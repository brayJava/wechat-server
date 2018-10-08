package com.bray.model.Bo;

import lombok.Data;

/**
 * @Author:wuzhiyuan
 * @Description: 文章图片相关实体类
 * @Date: Created in 23:57 2018/10/7
 * @Modified By:
 */
@Data
public class ArticleImgModelVo {

    private int articleImgId;

    private String articleId;

    private String insertType;

    private String imgUrl;
}
