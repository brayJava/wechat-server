package com.bray.service;

import com.bray.model.WyArticle;

/**
 * @Author:wuzhiyuan
 * @Description: 微信授权接口
 * @Date: Created in 10:01 2018/8/15
 * @Modified By:
 */
public interface IWechatAuthService {
    /**
     * 微信授权签名
     *
     * @param currentDomain
     * @param linkUrl
     * @param domainVerfiy
     * @return
     */
    String signature(String currentDomain, String linkUrl, String domainVerfiy);
}

