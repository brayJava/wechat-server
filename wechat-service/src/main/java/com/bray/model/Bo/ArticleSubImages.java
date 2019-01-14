package com.bray.model.Bo;

import com.bray.model.WyArticleImg;
import com.bray.model.WySubImg;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 8:52 2019/1/14
 * @Modified By:
 */
@Data
public class ArticleSubImages implements Serializable{

    private WyArticleImg wyArticleImg;

    private WySubImg subimg1;

    private WySubImg subimg2;

    private WySubImg subimg3;

}
