package com.bray.service;

import com.bray.model.Bo.ArticleImgModelVo;

/**
 * @Author:wuzhiyuan
 * @Description: 文章管理接口
 * @Date: Created in 11:05 2018/9/5
 * @Modified By:
 */
public interface IArticleAdminService {
    /**
     * 插入文章
     * @param obj
     */
    String insertArticle(Object obj);
    /**
     * 插入文章图片
     * @param articleId
     * @param imgPath
     */
    void insertArticleImg(int articleId,String imgPath);
    /**
     * 插入文章图片
     * @param obj
     */
    void insertOtherArticleImg(Object obj);
    /**
     * 修改文章
     */
    void updateArticle(Object obj);
    /**
     * 更新文章至线上
     */
    void articleRefresh(int id,String othervalue);
    /**
     * 文章图片删除
     * @Param articleImgId 文章图片id
     */
    void delArticleImg(int articleImgId);
    /**
     * 文章图片删除
     * @Param articleImgId 文章图片id
     * @Param imgUrl 图片链接地址
     * @Param imgUrlHref 图片超导链接
     * @Param subimgs 子连接图片
     */
    void updateArticleImg(int articleImgId, String imgUrl, String imgUrlHref,String subimgs);
    /**
     * 文章图片删除
     */
    void insertArticleImg(ArticleImgModelVo articleImgModelVo);
    /**
     * 复制文章
     * @param articleId 文章id
     */
    void articleCopy(int articleId);
    /**
     * 删除文章
     * @param articleId 文章id
     */
    void articleDel(int articleId);
}
