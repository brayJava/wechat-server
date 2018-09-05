package com.bray.model.Vo;

import lombok.Data;
import lombok.experimental.var;

/**
 * @Author:wuzhiyuan
 * @Description: 文章编辑类
 * @Date: Created in 9:21 2018/9/5
 * @Modified By:
 */
@Data
public class ArticleModelVo {
    /**
     * 文章标题
     */
    private String title;
    /**
     * 背景音乐
     */
    private String bgmUrl;
    /**
     * 分享标题
     */
    private String shareTitle;
    /**
     * 分享描述
     */
    private String shareDecribe;
    /**
     * 发布作者
     */
    private String author;
    /**
     * 是否存放订单图片
     */
    private String whetherOrderImg;
    /**
     * 是否发布
     */
    private String whetherPublish;

}