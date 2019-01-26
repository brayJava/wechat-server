package com.bray.service.impl;

import com.bray.aop.annotation.QueryCache;
import com.bray.aop.cache.RedisPoolCache;
import com.bray.dto.ConstatFinal;
import com.bray.dto.EffectiveType;
import com.bray.mapper.WyArticleImgMapper;
import com.bray.mapper.WyArticleMapper;
import com.bray.mapper.WySubImgMapper;
import com.bray.model.*;
import com.bray.model.Bo.ArticleSubImages;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.Vo.ArticleImg;
import com.bray.model.Vo.ArticleNewImages;
import com.bray.model.Vo.ArticleVo;
import com.bray.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    private WySubImgMapper wySubImgMapper;
    @Resource
    private RedisPoolCache redisCache;
    /**
     * 查询当前文章
     * @param articleId
     * @return
     */
    @Override
    @QueryCache(serviceType = ConstatFinal.AUTHOR)
    public ArticleWithImages queryCurrentArticle(int articleId) {
        return getArticleWithImages(articleId);
    }
    /**
     * 查询当前文章
     * @param articleId
     * @return
     */
    @Override
    public ArticleWithImages queryCurrentArticleBySql(int articleId) {
        return getArticleWithImages(articleId);
    }
    /**
     * 查询当前文章
     * @param articleId
     * @return
     */
    @Override
    @QueryCache(serviceType = ConstatFinal.NO_SHARE_DOMAIN)
    public String getNoShareDomainByArticleId(int articleId) {
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
    private ArticleWithImages getArticleWithImages(int articleId) {
        ArticleWithImages articleWithImages = new ArticleWithImages();
        List<ArticleSubImages> subImages = new ArrayList<>();
        if(!StringUtils.isEmpty(articleId)) {
            WyArticleExample wyArticleExample = new WyArticleExample();
            wyArticleExample.createCriteria().andIdEqualTo(articleId).andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
            //获取文章属性
            List<WyArticle> wyArticles = wyArticleMapper.selectByExample(wyArticleExample);
            if(CollectionUtils.isEmpty(wyArticles)) return new ArticleWithImages();
            WyArticle wyArticle = wyArticles.get(0);
            Object redisValueByKey = redisCache.getRedisValueByKey(ConstatFinal.ARTICLE_H5_2_VAL + ":" + articleId);
            if(!Objects.isNull(redisValueByKey)) {
                articleWithImages.setContentHtml(String.valueOf(redisValueByKey));
            }
            articleWithImages.setWyArticle(wyArticle);
            //获取文章对应图片
            WyArticleImgExample wyArticleImgExample = new WyArticleImgExample();
                wyArticleImgExample.createCriteria().andArticleIdEqualTo(articleId).andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
            List<WyArticleImg> wyArticleImgs = wyArticleImgMapper.selectByExample(wyArticleImgExample);
            wyArticleImgs.stream().forEach(wyArticleImg -> {
                ArticleSubImages articleSubImages = new ArticleSubImages();
                WySubImgExample wySubImgExample = new WySubImgExample();
                wySubImgExample.createCriteria().andImgIdEqualTo(wyArticleImg.getId());
                List<WySubImg> wySubImgs = wySubImgMapper.selectByExample(wySubImgExample);
                articleSubImages.setWyArticleImg(wyArticleImg);
                if(!CollectionUtils.isEmpty(wySubImgs)) {
                   articleSubImages.setSubimg1(wySubImgs.get(0));
                   articleSubImages.setSubimg2(wySubImgs.get(1));
                   articleSubImages.setSubimg3(wySubImgs.get(2));
                }
                subImages.add(articleSubImages);
            });

            // articleWithImages.setWyArticleImgs(wyArticleImgs);
            articleWithImages.setArticleSubImages(subImages);

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
    /**
     * 获取防封新文章
     * @param articleId 文章id
     * @return
     */
    @Override
    @QueryCache(serviceType = ConstatFinal.NEW_ARTICLE_LIST)
    public ArticleNewImages queryNewArticleImages(int articleId) {
        ArticleWithImages articleWithImages = this.getArticleWithImages(articleId);
        ArticleNewImages articleNewImages = new ArticleNewImages();
        ArticleVo articleVo = new ArticleVo();
        if(Objects.isNull(articleWithImages.getWyArticle())) {
            articleNewImages.setStatus("fail");
        } else {
            WyArticle wyArticle = articleWithImages.getWyArticle();
            articleVo.setImgnews_id(wyArticle.getId());
            articleVo.setImgnews_title(wyArticle.getTitle());
            articleVo.setImgnews_status("1");
            articleVo.setImgnews_bgm_url(wyArticle.getBgmUrl());
            articleVo.setImgnews_forcedShare(wyArticle.getForcedShare());
            articleVo.setImgnews_shareimg_url(wyArticle.getShareImgUrl());
            articleVo.setImgnews_cate("4");
            articleVo.setImgnews_keyword("x");
            articleVo.setImgnews_description(wyArticle.getShareDescribe());
            articleVo.setImgnews_create_date(Clock.systemDefaultZone().millis()+"");
            articleVo.setImgnews_view_number("1");
            articleVo.setImgnews_total(wyArticle.getStatistical());
            articleVo.setImgnews_goback_url(wyArticle.getGobackUrl());
            articleVo.setImgnews_image_goback_url(wyArticle.getImageGobackUrl());
            articleVo.setDataTransferUrl(wyArticle.getDataTransferUrl());
            articleVo.setImgnews_read_riginal_Url(wyArticle.getReadOriginalUrl());
            articleVo.setImgnews_read_noshareDomain(wyArticle.getNoShareDomain());
            List<ArticleImg> articleImgs = new ArrayList<>();
            if(!StringUtils.isEmpty(articleWithImages.getWyArticleImgs())) {
                List<WyArticleImg> wyArticleImgs = articleWithImages.getWyArticleImgs();
                wyArticleImgs.stream().forEach(wyArticleImg -> {
                    ArticleImg articleImg = new ArticleImg();
                    articleImg.setItem_id(wyArticleImg.getId()+"");
                    articleImg.setItem_img_url(wyArticleImg.getThirdImgPath());
                    articleImg.setItem_href(StringUtils.isEmpty(wyArticleImg.getImgUrl()) ? "#" : wyArticleImg.getImgUrl());
                    articleImg.setItem_news_id(wyArticleImg.getArticleId());
                    articleImgs.add(articleImg);
                });
            }
            articleVo.setItems(articleImgs);
        }
        articleNewImages.setData(articleVo);
        return articleNewImages;
    }
}