package com.bray;

import com.alibaba.fastjson.JSONObject;
import com.bray.aop.cache.RedisCache;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.service.IArticleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 1:50 2018/9/9
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class RedisServerTest {


    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IArticleService iArticleService;

    @Test
    public void updateArticle() {
//        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle("d8ef9cd857af4292b80d8e11a4fc17fd");
        Object s = redisCache.getRedisValueByKey("LM:d8ef9cd857af4292b80d8e11a4fc17fd");
        Object redisValueByKey = redisCache.getRedisValueByKey("LM:731cf62451ff44bb8b28101996fd5a3b");
        System.out.println(JSONObject.toJSONString(s));
        Assert.assertNotNull(s);

    }
}
