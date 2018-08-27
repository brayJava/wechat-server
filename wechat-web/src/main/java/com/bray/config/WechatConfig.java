package com.bray.config;

import com.foxinmy.weixin4j.cache.FileCacheStorager;
import com.foxinmy.weixin4j.cache.MemcacheCacheStorager;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.model.WeixinAccount;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:wuzhiyuan
 * @Description: 微信授权初始化
 * @Date: Created in 10:37 2018/8/15
 * @Modified By:
 */
@Configuration
public class WechatConfig {

    @Value("${weixin4j.cache}")
    private String weixinCache;
    @Value("${weixin4j.id}")
    private String weixinId;
    @Value("${weixin4j.secret}")
    private String weixinSecret;
    /**
     * 实例化微信代理类
     * @return
     */
    @Bean
    public WeixinProxy initWeixinProxy() {
        WeixinProxy weixinProxy = null;
        if("FILE".equals(weixinCache)) {
            weixinProxy = new WeixinProxy(new WeixinAccount(weixinId,weixinSecret),new FileCacheStorager<Token>());
        } else {
            weixinProxy = new WeixinProxy(new WeixinAccount(weixinId,weixinSecret),new MemcacheCacheStorager<Token>());
        }
        return weixinProxy;
    }
}
