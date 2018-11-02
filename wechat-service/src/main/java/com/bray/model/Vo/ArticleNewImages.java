package com.bray.model.Vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:wuzhiyuan
 * @Description: 文章图片
 * @Date: Created in 13:04 2018/11/2
 * @Modified By:
 */
@Data
public class ArticleNewImages implements Serializable{

    private ArticleVo data;

    private String status;

    public ArticleNewImages() {
        this.status = "ok";
    }
}
