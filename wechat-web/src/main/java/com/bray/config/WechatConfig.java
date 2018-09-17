package com.bray.config;

import com.bray.dto.EffectiveType;
import com.bray.mapper.WyWechatAuthMapper;
import com.bray.model.WyWechatAuth;
import com.bray.model.WyWechatAuthExample;
import com.foxinmy.weixin4j.cache.FileCacheStorager;
import com.foxinmy.weixin4j.cache.MemcacheCacheStorager;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.model.WeixinAccount;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 微信授权初始化
 * @Date: Created in 10:37 2018/8/15
 * @Modified By:
 */
//@Configuration
public class WechatConfig {

    @Value("${weixin4j.cache}")
    private String weixinCache;
    @Value("${weixin4j.id}")
    private String weixinId;
    @Value("${weixin4j.secret}")
    private String weixinSecret;
    @Resource
    private WyWechatAuthMapper wyWechatAuthMapper;
    /**
     * 实例化微信代理类
     * @return
     */
    @Bean
    public WeixinProxy initWeixinProxy() {
        WyWechatAuthExample wyWechatAuthExample = new WyWechatAuthExample();
        wyWechatAuthExample.createCriteria().andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
        wyWechatAuthExample.setOrderByClause("update_time desc limit 1");
        List<WyWechatAuth> wyWechatAuths = wyWechatAuthMapper.selectByExample(wyWechatAuthExample);
        WyWechatAuth wyWechatAuth = wyWechatAuths.get(0);
        WeixinProxy weixinProxy = null;
        if("FILE".equals(wyWechatAuth.getWeixinCache())) {
            weixinProxy = new WeixinProxy(new WeixinAccount(wyWechatAuth.getWeixinId(),wyWechatAuth.getWeixinSecret()),new FileCacheStorager<Token>());
        } else {
            weixinProxy = new WeixinProxy(new WeixinAccount(wyWechatAuth.getWeixinId(),wyWechatAuth.getWeixinSecret()),new MemcacheCacheStorager<Token>());
        }
        return weixinProxy;
    }
}
