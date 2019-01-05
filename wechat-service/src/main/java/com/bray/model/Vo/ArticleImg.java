package com.bray.model.Vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:wuzhiyuan
 * @Description: 文章图片
 * @Date: Created in 13:23 2018/11/2
 * @Modified By:
 */
@Data
public class ArticleImg implements Serializable{

    private String item_id;

    private int item_news_id;

    private String item_img_url;

    private String item_href;

}