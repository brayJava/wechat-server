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

    private String[] outerImgUrl;
}
