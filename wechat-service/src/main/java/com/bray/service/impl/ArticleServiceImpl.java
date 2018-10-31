package com.bray.service.impl;

import com.bray.aop.annotation.QueryCache;
import com.bray.aop.cache.RedisPoolCache;
import com.bray.dto.ConstatFinal;
import com.bray.dto.EffectiveType;
import com.bray.mapper.WyArticleImgMapper;
import com.bray.mapper.WyArticleMapper;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.WyArticle;
import com.bray.model.WyArticleExample;
import com.bray.model.WyArticleImg;
import com.bray.model.WyArticleImgExample;
import com.bray.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 文章业务实现
 * @Date: Created in 13:17 2018/9/5
 * @Modified By:
 */
@Service
@Slf4j
public class ArticleServiceImpl implements IArticleService{

    @Resource
    private WyArticleMapper wyArticleMapper;
    @Resource
    private WyArticleImgMapper wyArticleImgMapper;
    @Resource
    private RedisPoolCache redisCache;
    /**
     * 查询当前文章
     * @param articleId
     * @return
     */
    @Override
    @QueryCache(serviceType = ConstatFinal.AUTHOR)
    public ArticleWithImages queryCurrentArticle(String articleId) {
        return getArticleWithImages(articleId);
    }
    /**
     * 查询当前文章
     * @param articleId
     * @return
     */
    @Override
    public ArticleWithImages queryCurrentArticleBySql(String articleId) {
        return getArticleWithImages(articleId);
    }
    /**
     * 查询当前文章
     * @param articleId
     * @return
     */
    @Override
    @QueryCache(serviceType = ConstatFinal.NO_SHARE_DOMAIN)
    public String getNoShareDomainByArticleId(String articleId) {
        WyArticleExample wyArticleExample = new WyArticleExample();
        wyArticleExample.createCriteria().andIdEqualTo(articleId).andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
        //获取文章
        List<WyArticle> wyArticles = wyArticleMapper.selectByExample(wyArticleExample);
        if(CollectionUtils.isEmpty(wyArticles)) return "";
        WyArticle wyArticle = wyArticles.get(0);
        //取非分享域名，并随机存储一个域名
        String noShareDomain = wyArticle.getNoShareDomain();
        if(!StringUtils.isEmpty(noShareDomain)) {
            String[] noShareDomains = noShareDomain.split(",");
            int v = (int)Math.floor(Math.random() * noShareDomains.length); //在域名中求整数
            log.info("----------文章id：{}获取的非分享域名为：{}",articleId,noShareDomains[v]);
            return noShareDomains[v];
        }
        return "";
    }
    /**
     * 查询所有文章
     * @return
     */
    @Override
    public  List<WyArticle> queryAllEffectiveArticle() {

        WyArticleExample wyArticleExample = new WyArticleExample();
        wyArticleExample.createCriteria().andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
        wyArticleExample.setOrderByClause("create_time desc");
        List<WyArticle> wyArticles = wyArticleMapper.selectByExample(wyArticleExample);
        if(CollectionUtils.isEmpty(wyArticles)) return new ArrayList<WyArticle>();
        return wyArticles;
    }
    /**
     * 获取文章具体图文信息
     * @param articleId
     * @return
     */
    private ArticleWithImages getArticleWithImages(String articleId) {
        ArticleWithImages articleWithImages = new ArticleWithImages();
        if(!StringUtils.isEmpty(articleId)) {
            WyArticleExample wyArticleExample = new WyArticleExample();
            wyArticleExample.createCriteria().andIdEqualTo(articleId).andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
            //获取文章属性
            List<WyArticle> wyArticles = wyArticleMapper.selectByExample(wyArticleExample);
            if(CollectionUtils.isEmpty(wyArticles)) return new ArticleWithImages();
            articleWithImages.setWyArticle(wyArticles.get(0));
            //获取文章对应图片
            WyArticleImgExample wyArticleImgExample = new WyArticleImgExample();
            wyArticleImgExample.createCriteria().andArticleIdEqualTo(articleId).andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
            List<WyArticleImg> wyArticleImgs = wyArticleImgMapper.selectByExample(wyArticleImgExample);
            articleWithImages.setWyArticleImgs(wyArticleImgs);
        }
        return articleWithImages;
    }
    /**
     * @param timestmpStr 时间戳字符串
     * @return
     */
    @Override
    @QueryCache(serviceType = ConstatFinal.AITICLE_URL_TIME)
    public String queryExistHtmlUrl(String timestmpStr) {

        return "";
    }
    /**
     * 缓存唯一时间戳链接
     * @param timestmpStr
     */
    @Override
    public void insertHtmlUrlToRedis(String timestmpStr) {
        try {
            redisCache.saveDataToRedis(ConstatFinal.AITICLE_URL_TIME+":"+timestmpStr,timestmpStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}