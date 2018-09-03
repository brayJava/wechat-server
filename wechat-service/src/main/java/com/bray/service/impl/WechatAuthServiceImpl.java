package com.bray.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bray.service.IWechatAuthService;
import com.bray.util.WeixinJSAuthorization;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.type.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String signature(String linkUrl) {

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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("signature",signature);
        jsonObject.put("noncestr",noncestr);
        jsonObject.put("timestamp",String.valueOf(timestamp));
        jsonObject.put("accesstoken",ticketManagerCache.getAccessToken());
        return jsonObject.toJSONString();
    }
}
