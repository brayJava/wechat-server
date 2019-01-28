package com.bray.task;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.dto.ConstatFinal;
import com.bray.model.WyArticle;
import com.bray.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 文章处理先关定任务
 * @Date: Created in 16:39 2018/10/10
 * @Modified By:
 */
// @Service
@Slf4j
public class ArticleServiceTask {

    @Resource
    private IArticleService articleService;

    @Resource
    private RedisPoolCache redisCache;

    /**
     * 每十分钟处理一次非分享域名redis
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void ArticleNoShareDomain() {
        List<WyArticle> wyArticles = articleService.queryAllEffectiveArticle(1);
        //处理每一篇文章的非强制分享域名
        wyArticles.stream().forEach(wyArticle -> {
            //循环删除所有非分享redis存储
            redisCache.deleteDataOfRedis(ConstatFinal.NO_SHARE_DOMAIN+":"+wyArticle.getId());
            log.info("-------删除文章非分享域名redis键值"+ConstatFinal.NO_SHARE_DOMAIN+":"+wyArticle.getId());
        });
    }
}
