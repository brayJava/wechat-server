package com.bray;

import com.alibaba.fastjson.JSONObject;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.Vo.ArticleNewImages;
import com.bray.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author:wuzhiyuan
 * @Description: 文章业务测试
 * @Date: Created in 13:34 2018/9/5
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@Slf4j
public class ArticleServieTest {

    @Resource
    private IArticleService iArticleService;
    @Test
    public void queryArticleById() {
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle("1f749c7657364920924c4d3d407463dd");
        log.info("返回文章属性：{}", JSONObject.toJSONString(articleWithImages));
        Assert.assertNotNull(articleWithImages);

    }
    @Test
    public void queryNewArticleById() {
        ArticleNewImages articleNewImages = iArticleService.queryNewArticleImages("a1d9b1e5d8f44a1e9e1e376a25c2ec0d");
        log.info("返回文章属性：{}", JSONObject.toJSONString(articleNewImages));
        Assert.assertNotNull(articleNewImages);

    }
}
