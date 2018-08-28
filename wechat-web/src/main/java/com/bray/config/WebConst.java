package com.bray.config;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author:wuzhiyuan
 * @Description: 启动参数初始化
 * @Date: Created in 10:45 2018/8/28
 * @Modified By:
 */
@Component
public class WebConst {
    /**
     * 初始化域名容器
     */
    public static Map<String,Object> domainMap = new HashMap<>();
    /**
     * 分享主域名
     */
    public static String SHARE_DOMAIN = "share_domain";
    /**
     * 普通主域名
     */
    public static String COMMON_DOMAIN = "common_domain";
    /**
     * 分享子域名（二级域名）
     */
    public static String SUB_SHARE_DOMAIN = "sub_share_domain";
    /**
     * 普通子域名（二级域名）
     */
    public static String SUB_COMMON_DOMAIN = "sub_common_domain";
    /**
     * 所有域名管理
     */
    public static String ALL_DOMAIN = "all_domain";
}
