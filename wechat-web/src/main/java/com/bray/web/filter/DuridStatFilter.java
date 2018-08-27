package com.bray.web.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @Author:wuzhiyuan
 * @Description: 配合druid监控的使用配置
 * @Date: Created in 12:31 2018/7/21
 * @Modified By:
 */
@WebFilter(filterName = "druidWebStatFilter" ,urlPatterns = "/*",
initParams = {
        @WebInitParam(name = "exclusions",value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/durid/*"),// 忽略的资源
        @WebInitParam(name = "sessionStatEnable", value = "true"),
        @WebInitParam(name = "profileEnable", value = "true")
})
public class DuridStatFilter extends WebStatFilter {
}
