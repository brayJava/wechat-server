package com.bray.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author:wuzhiyuan
 * @Description: 注册redisTemplate，作为消息队列的发布者
 * @Date: Created in 11:45 2019/1/16
 * @Modified By:
 */
@Configuration
public class PublisherConfig {

    @Bean
    public StringRedisTemplate getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }
}
