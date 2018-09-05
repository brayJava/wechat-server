package com.bray.service;

/**
 * @Author:wuzhiyuan
 * @Description: 文章管理接口
 * @Date: Created in 11:05 2018/9/5
 * @Modified By:
 */
public interface IArticleAdminService {
    /**
     * 插入文章
     * @param articleModelVo
     */
    String insertArticle(Object articleModelVo);
    /**
     * 插入文章图片
     * @param articleId
     * @param imgPath
     */
    void insertArticleImg(String articleId,String imgPath);
}
