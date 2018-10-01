package com.bray.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bray.dto.EffectiveType;
import com.bray.mapper.WyWechatAuthMapper;
import com.bray.model.WyArticle;
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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class WechatAuthServiceImpl implements IWechatAuthService {
    @Resource
    private WyWechatAuthMapper wyWechatAuthMapper;
    /**
     * 授权接口实现
     * @param currentDomain 访问域名
     * @param linkUrl       访问连接
     * @param domainVerfiy  域名校验，用于区分公众号
     * @return
     */
    @Override
    public String signature(String linkUrl, String domainVerfiy,String currentDomain) {
        //获取微信公众号信息
        List<WyWechatAuth> wyWechatAuths = getWyWechatAuths();
        JSONObject jsonObject = new JSONObject();
        WyWechatAuth wyWechatAuth = new WyWechatAuth();
        if(!CollectionUtils.isEmpty(wyWechatAuths)) {
            //域名校验匹配公众号，发现为配置的，则获取
            for (WyWechatAuth wechatAuth:wyWechatAuths) {
                if(wechatAuth.getBindDomains().contains(domainVerfiy)) {
                    wyWechatAuth = wechatAuth;
                    break;
                }
            }
            log.info("-------当前域名为：{}，------签名数据为：{}",domainVerfiy,JSONObject.toJSONString(wyWechatAuth));
            WeixinProxy weixinProxy = new WeixinProxy(new WeixinAccount(wyWechatAuth.getWeixinId()
                   ,wyWechatAuth.getWeixinSecret()),new FileCacheStorager<Token>());
            TokenManager ticketManager = weixinProxy.getTicketManager(TicketType.jsapi);
            Token ticketManagerCache = null;
            try {
                ticketManagerCache = ticketManager.getCache();
            } catch (WeixinException e) {
                e.printStackTrace();
            }
            if(Objects.isNull(ticketManagerCache)){
                jsonObject.put("contentUrl",currentDomain);
                return jsonObject.toString();
            }
            Integer timestamp = Integer.valueOf(String.valueOf(ticketManagerCache.getCreateTime()/1000));
            String noncestr = WeixinJSAuthorization.getNoncestr(17);
            String signature = WeixinJSAuthorization.getSignature(ticketManagerCache.getAccessToken(), timestamp.toString(), noncestr, linkUrl);
            jsonObject.put("signature",signature);
            jsonObject.put("noncestr",noncestr);
            jsonObject.put("timestamp",String.valueOf(timestamp));
            jsonObject.put("accesstoken",ticketManagerCache.getAccessToken());
            jsonObject.put("theAppId",wyWechatAuth.getWeixinId());
            jsonObject.put("contentUrl",currentDomain);
        }
        return jsonObject.toJSONString();
    }
    /**
     * 获取授权信息
     * @return
     */
    private List<WyWechatAuth> getWyWechatAuths() {
        WyWechatAuthExample wyWechatAuthExample = new WyWechatAuthExample();
        wyWechatAuthExample.createCriteria().andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
        wyWechatAuthExample.setOrderByClause("update_time desc");
        return wyWechatAuthMapper.selectByExample(wyWechatAuthExample);
    }
}