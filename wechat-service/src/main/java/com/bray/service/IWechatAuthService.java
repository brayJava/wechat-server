package com.bray.service;

/**
 * @Author:wuzhiyuan
 * @Description: 微信授权接口
 * @Date: Created in 10:01 2018/8/15
 * @Modified By:
 */
public interface IWechatAuthService {
    /**
     * 微信授权签名
     * @param linkUrl
     * @return
     */
    String signature(String linkUrl);
}

