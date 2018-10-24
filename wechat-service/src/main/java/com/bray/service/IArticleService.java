package com.bray.service;

import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.WyArticle;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 文章业务管理
 * @Date: Created in 13:16 2018/9/5
 * @Modified By:
 */
public interface IArticleService {
    /**
     * 查询相应id文章
     * @param articleId
     * @return
     */
    ArticleWithImages queryCurrentArticle(String articleId);
    /**
     * 查询数据库中信息
     * @param articleId
     * @return
     */
    ArticleWithImages queryCurrentArticleBySql(String articleId);
    /**
     * 查询所有文章
     * @return
     */
    List<WyArticle> queryAllEffectiveArticle();
    /**
     * 通过文章id获取非分享唯一域名
     * @return
     */
    String getNoShareDomainByArticleId(String articleId);
    /**
     * 获取文章唯一时间戳
     * @param timestmpStr 时间戳字符串
     * @return
     */
    String queryExistHtmlUrl(String timestmpStr);
    /**
     * 向redis中缓存时间搓，唯一表示访问过的连接，不再提供文章展开权限
     * @param timestmpStr
     */
    void insertHtmlUrlToRedis(String timestmpStr);
}
