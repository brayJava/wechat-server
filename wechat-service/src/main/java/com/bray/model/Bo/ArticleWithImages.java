package com.bray.model.Bo;

import com.bray.model.WyArticle;
import com.bray.model.WyArticleImg;
import lombok.Data;

import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 文章图片类
 * @Date: Created in 13:20 2018/9/5
 * @Modified By:
 */
@Data
public class ArticleWithImages {

    private WyArticle wyArticle;

    private List<WyArticleImg> wyArticleImgs;
}
