package com.bray.dto;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 21:35 2018/9/8
 * @Modified By:
 */
public interface ConstatFinal {
    //redis key 值相关
    String AUTHOR = "LM";

    String IMAGES_LIST = "images_list";

    String DOMAIN_MAP = "domain_map";

    //查询类型
    String QUERY_ADMIN="query_admin";

    String QUERY_WEB="query_web";

    //redis key 值相关
    String NO_SHARE_DOMAIN = "no_share_domain";
    //redis key 值相关,文章链接时间戳
    String AITICLE_URL_TIME = "article_url_time";
    //新文章获取
    String NEW_ARTICLE_LIST = "new_article_list";
    //新文章获取
    String IMAGES_CONTENT = "images_content";
    //新文章获取
    String ARTICLE_CONTENT = "article_list";
    //新文章获取
    String ARTICLENEW_CONTENT = "articlenew_list";

    String IFRAMELIST = "iframe_list";


}
