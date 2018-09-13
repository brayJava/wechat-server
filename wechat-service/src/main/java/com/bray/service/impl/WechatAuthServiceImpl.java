package com.bray.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bray.dto.EffectiveType;
import com.bray.mapper.WyWechatAuthMapper;
import com.bray.model.WyWechatAuth;
import com.bray.model.WyWechatAuthExample;
import com.bray.service.IWechatAuthService;
import com.bray.util.WeixinJSAuthorization;
import com.foxinmy.weixin4j.cache.FileCacheStorager;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.model.WeixinAccount;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.type.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
/**
 * @Author:wuzhiyuan
 * @Description: 获取签字符串
 * @Date: Created in 10:04 2018/8/15
 * @Modified By:
 */
@Service
public class WechatAuthServiceImpl implements IWechatAuthService {

    @Autowired
    private WeixinProxy weixinProxy;
    @Value("${weixin4j.id}")
    private String appId;
    @Resource
    private WyWechatAuthMapper wyWechatAuthMapper;

    @Override
    public String signature(String linkUrl) {
//        WyWechatAuthExample wyWechatAuthExample = new WyWechatAuthExample();
//        wyWechatAuthExample.createCriteria().andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
//        wyWechatAuthExample.setOrderByClause("update_time desc limit 1");
//        List<WyWechatAuth> wyWechatAuths = wyWechatAuthMapper.selectByExample(wyWechatAuthExample);
        JSONObject jsonObject = new JSONObject();
//        if(!CollectionUtils.isEmpty(wyWechatAuths)) {
//            WyWechatAuth wyWechatAuth = wyWechatAuths.get(0);
//            WeixinProxy weixinProxy = new WeixinProxy(new WeixinAccount(wyWechatAuth.getWeixinId()
//                    ,wyWechatAuth.getWeixinSecret()),new FileCacheStorager<Token>());
        TokenManager ticketManager = weixinProxy.getTicketManager(TicketType.jsapi);
        Token ticketManagerCache = null;
        try {
            ticketManagerCache = ticketManager.getCache();
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        if(Objects.isNull(ticketManagerCache)) return "{}";
        Integer timestamp = Integer.valueOf(String.valueOf(ticketManagerCache.getCreateTime()/1000));
        String noncestr = WeixinJSAuthorization.getNoncestr(17);
        String signature = WeixinJSAuthorization.getSignature(ticketManagerCache.getAccessToken(), timestamp.toString(), noncestr, linkUrl);

        jsonObject.put("signature",signature);
        jsonObject.put("noncestr",noncestr);
        jsonObject.put("timestamp",String.valueOf(timestamp));
        jsonObject.put("accesstoken",ticketManagerCache.getAccessToken());
        jsonObject.put("theAppId",appId);
//        }
        return jsonObject.toJSONString();
    }
}
