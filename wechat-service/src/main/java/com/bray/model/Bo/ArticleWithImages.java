package com.bray.model.Bo;

import com.bray.model.WyArticle;
import com.bray.model.WyArticleImg;
import com.bray.model.WySubImg;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 文章图片类
 * @Date: Created in 13:20 2018/9/5
 * @Modified By:
 */
@Data
public class ArticleWithImages implements Serializable{

    private WyArticle wyArticle;
    /**
     * 图片内容页
     */
    private String contentHtml;
    /**
     * 域名连接
     */
    private String domainUrl;
    /**
     * 分享域名连接
     */
    private String shareUrl;

    private List<WyArticleImg> wyArticleImgs;

    private List<ArticleSubImages> articleSubImages;
}
