package com.bray;

import com.alibaba.fastjson.JSONObject;
import com.bray.aop.cache.RedisPoolCache;
import com.bray.service.IArticleService;
import com.foxinmy.weixin4j.tuple.Article;
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
    private RedisPoolCache redisCache;
    @Autowired
    private IArticleService iArticleService;

    @Test
    public void updateArticle() {
//        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle("d8ef9cd857af4292b80d8e11a4fc17fd");
//      redisCache.deleteDataOfRedis("test");
        Article article = new Article("wode","desw","http://localhsot","dfewf");
//        redisCache.saveDataToRedis("redisTest",article);
        Object redisTest = redisCache.getRedisValueByKey("domain_map");
        System.out.println(JSONObject.toJSONString(redisTest));

    }
}
