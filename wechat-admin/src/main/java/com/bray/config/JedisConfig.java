package com.bray.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import static com.bray.config.BaseConfig.JEDIS_POOL;
import static com.bray.config.BaseConfig.JEDIS_POOL_CONFIG;

/**
 * @Author:wuzhiyuan
 * @Description: redis连接池初始化配置
 * @Date: Created in 13:16 2018/8/14
 * @Modified By:
 */
@Configuration
public class JedisConfig {
    //javaConfig配置方式
    @Bean(name = JEDIS_POOL)
    public JedisPool jedisConfig(@Qualifier(JEDIS_POOL_CONFIG) JedisPoolConfig jedisPoolConfig,
                                   @Value("${spring.jedis.pool.host}")String host,
                                   @Value("${spring.jedis.pool.port}")int port,
                                   @Value("${spring.jedis.pool.timeout}")int timeout,
                                   @Value("${spring.jedis.pool.password}")String password) {
        return new JedisPool(jedisPoolConfig,host,port,timeout,password);
    }
    /**
     * jedis连接池配置
     * @param maxTotal      最大连接数量
     * @param maxIdle
     * @param maxWaitMillis 最长等待时间
     * @return
     */
    @Bean(name = JEDIS_POOL_CONFIG)
    public JedisPoolConfig jedisPoolConfig(@Value("${spring.jedis.pool.config.maxTotal}") int maxTotal,
                                           @Value("${spring.jedis.pool.config.maxIdle}") int maxIdle,
                                           @Value("${spring.jedis.pool.config.maxWaitMillis}") int maxWaitMillis) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return jedisPoolConfig;
    }
}
