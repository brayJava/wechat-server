package com.bray.service.impl;

import com.bray.dto.ConstFinal;
import com.bray.dto.EffectiveType;
import com.bray.mapper.WyArticleImgMapper;
import com.bray.mapper.WyArticleMapper;
import com.bray.model.Vo.ArticleModelVo;
import com.bray.model.Vo.ArticleOtherModelVo;
import com.bray.model.WyArticle;
import com.bray.model.WyArticleImg;
import com.bray.service.IArticleAdminService;
import com.bray.util.GUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
/**
 * @Author:wuzhiyuan
 * @Description: 文章管理接口
 * @Date: Created in 11:10 2018/9/5
 * @Modified By:
 */
@Service
@Slf4j
@Transactional
public class ArticleAdminServiceImpl implements IArticleAdminService {
    @Resource
    private WyArticleMapper wyArticleMapper;
    @Resource
    private WyArticleImgMapper wyArticleImgMapper;
    /**
     * 新增文章
     * @param articleModelVo
     */
    @Override
    public String insertArticle(Object articleModelVo) {
        String articleId = GUIDUtil.buildMd5GUID();
        ArticleModelVo article = (ArticleModelVo) articleModelVo;
        WyArticle wyArticle = new WyArticle();
        wyArticle.setId(articleId);
        wyArticle.setTitle(article.getTitle());
        wyArticle.setBgmUrl(article.getBgmUrl());
        wyArticle.setAuthor(article.getAuthor());
        wyArticle.setShareTitle(article.getShareTitle());
        wyArticle.setShareDescribe(article.getShareDecribe());
        wyArticle.setIsOrderImg((ConstFinal.ARTICLE_STATUS.equals(article.getWhetherOrderImg()) ? true : false));
        wyArticle.setIsPublish((ConstFinal.ARTICLE_STATUS.equals(article.getWhetherPublish()) ? true : false));
        wyArticle.setStatus(EffectiveType.EFFECTIVE_YES);
        wyArticle.setCreateTime(new Date());
        wyArticle.setUpdateTime(new Date());
        try {
            wyArticleMapper.insertSelective(wyArticle);
        } catch (RuntimeException e) {
            log.error("------------文章添加失败---------");
            e.printStackTrace();
        }
        return articleId;
    }
    /**
     * 插入文章图片
     * @param articleId
     * @param imgPath
     */
    @Override
    public void insertArticleImg(String articleId, String imgPath) {
        WyArticleImg wyArticleImg = new WyArticleImg();
        wyArticleImg.setArticleId(articleId);
        wyArticleImg.setImgPath(imgPath);
        wyArticleImg.setStatus(EffectiveType.EFFECTIVE_YES);
        wyArticleImg.setCreateTime(new Date());
        wyArticleImg.setUpdateTime(new Date());
        try {
            wyArticleImgMapper.insertSelective(wyArticleImg);
        } catch (RuntimeException e) {
            log.error("-------插入文章图片失败-------");
            e.printStackTrace();
        }
    }
    /**
     * 第二种添加文章方式
     */
    @Override
    public void insertOtherArticleImg(Object obj) {
        String articleId = GUIDUtil.buildMd5GUID();
        ArticleOtherModelVo articleOtherModelVo = (ArticleOtherModelVo) obj;
        WyArticle wyArticle = new WyArticle();
        wyArticle.setId(articleId);
        wyArticle.setTitle(articleOtherModelVo.getTitle());
        wyArticle.setBgmUrl(articleOtherModelVo.getBgmUrl());
        wyArticle.setAuthor(articleOtherModelVo.getAuthor());
        wyArticle.setShareTitle(articleOtherModelVo.getShareTitle());
        wyArticle.setShareDescribe(articleOtherModelVo.getShareDescribe());
        wyArticle.setIsOrderImg(ConstFinal.ARTICLE_STATUS.equals(articleOtherModelVo.getOrderImg()) ? true : false);
        wyArticle.setIsPublish((ConstFinal.ARTICLE_STATUS.equals(articleOtherModelVo.getOrderImg()) ? true : false));
        wyArticle.setStatus(EffectiveType.EFFECTIVE_YES);
        wyArticle.setCreateTime(new Date());
        wyArticle.setUpdateTime(new Date());
        try {
            wyArticleMapper.insertSelective(wyArticle);
        } catch (Exception e) {
            log.error("-----------文章添加失败----------");
            e.printStackTrace();
        }
        if(articleOtherModelVo.getOuterImgUrl().length > 0) {
            String[] outerImgUrls = articleOtherModelVo.getOuterImgUrl();
            Arrays.stream(outerImgUrls).forEach(outerImgUrl -> {
                WyArticleImg wyArticleImg = new WyArticleImg();
                wyArticleImg.setThirdImgPath(outerImgUrl);
                wyArticleImg.setStatus(EffectiveType.EFFECTIVE_YES);
                wyArticleImg.setArticleId(articleId);
                wyArticleImg.setUpdateTime(new Date());
                wyArticleImg.setCreateTime(new Date());
                try {
                    wyArticleImgMapper.insertSelective(wyArticleImg);
                } catch (Exception e) {
                    log.error("-----------关联图片添加失败----------");
                    e.printStackTrace();
                }
            });
        }
    }
}