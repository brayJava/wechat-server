package com.bray.service.impl;

import com.bray.dto.EffectiveType;
import com.bray.mapper.WyArticleImgMapper;
import com.bray.mapper.WyArticleMapper;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.WyArticle;
import com.bray.model.WyArticleExample;
import com.bray.model.WyArticleImg;
import com.bray.model.WyArticleImgExample;
import com.bray.service.IArticleService;
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
public class ArticleServiceImpl implements IArticleService{

    @Resource
    private WyArticleMapper wyArticleMapper;

    @Resource
    private WyArticleImgMapper wyArticleImgMapper;
    /**
     * 查询当前文章
     * @param articleId
     * @return
     */
    @Override
    public ArticleWithImages queryCurrentArticle(String articleId) {
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
     * 查询所有文章
     * @return
     */
    @Override
    public  List<WyArticle> queryAllEffectiveArticle() {

        WyArticleExample wyArticleExample = new WyArticleExample();
        wyArticleExample.createCriteria().andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
        wyArticleExample.setOrderByClause("update_time desc");
        List<WyArticle> wyArticles = wyArticleMapper.selectByExample(wyArticleExample);
        if(CollectionUtils.isEmpty(wyArticles)) return new ArrayList<WyArticle>();
        return wyArticles;
    }
}
