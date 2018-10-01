package com.bray.dto;

/**
 * @Author:wuzhiyuan
 * @Description: url相关
 * @Date: Created in 11:14 2018/8/28
 * @Modified By:
 */
public interface UrlConstant {
    /**
     * 图片服务器端口号
     */
    String PORT = "801";
    /**
     * 分享链接
     */
    String SHARE_URL = "share_url";
    /**
     * 内容链接
     */
    String CONTENT_URL = "content_url";
    /**
     * 非分享链接跳转
     */
    String WITH_OUT_SHARE = "withoutshare_url";
    /**
     * 域名名称
     */
    String DOMAIN_NAME = "domain_name";
    /**
     * http链接
     */
    String HTTP_RUL = "http://";
    /**
     * 内容链接跳转
     */
    String PATH_CONTENT_URL = "/wechat/random-content-other/";
    /**
     * 分享链接跳转
     */
    String PATH_JUMP_RUL = "/wechat/jump/";
    /**
     * 测试内容链接跳转
     */
    String PATH_CONTENT_URL_TEST = "/wechat/random-test/";
    /**
     * 测试分享链接跳转
     */
    String PATH_JUMP_RUL_TEST = "/wechat/jump-test/";
    /**
     * 非分享功能跳转
     */
    String PATH_WITHOUT_SHARE_RUL = "/wechat/random-common/";
}
