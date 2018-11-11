package com.bray.model.Vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 文章内容
 * @Date: Created in 13:08 2018/11/2
 * @Modified By:
 */
@Data
public class ArticleVo implements Serializable{

    private String imgnews_id;

    private String imgnews_cate;

    private String imgnews_title;

    private String imgnews_keyword;

    private String imgnews_description;

    private String imgnews_view_number;

    private String imgnews_create_date;

    private String imgnews_status;

    private String imgnews_total;

    private String imgnews_bgm_url;

    private String imgnews_goback_url;

    private String dataTransferUrl;

    private List<ArticleImg> items;

}