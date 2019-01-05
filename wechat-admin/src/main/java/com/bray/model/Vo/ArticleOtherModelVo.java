package com.bray.model.Vo;

import lombok.Data;

/**
 * @Author:wuzhiyuan
 * @Description: 文章编辑类
 * @Date: Created in 1:39 2018/9/7
 * @Modified By:
 */
@Data
public class ArticleOtherModelVo {

    private String title;

    private String bgmUrl;

    private String shareTitle;

    private String shareDescribe;

    private String shareImgUrl;

    private String author;

    private String orderImg;

    private int articleId;
    //数据迁移连接
    private String dataTransferUrl;

    private String gobackUrl;

    private String imageGobackUrl;

    private String readOriginalUrl;

    private String orderImgUrl;

    private String orderCopyUrl;

    private String shareSuccessFailUrl;
    //相关统计代码
    private String statistical;

    private String forcedShare;

    private String noShareDomain;

    private String[] outerImgUrl;

    private String imagesUrl;
}
