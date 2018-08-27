package com.bray.web.filter;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 12:38 2018/7/21
 * @Modified By:
 */
@WebServlet(urlPatterns = "/druid/*",
initParams = {
//        @WebInitParam(name = "allow" ,value = "127.0.0.1"),
//        @WebInitParam(name = "deny",value = ""),
        @WebInitParam(name = "loginUsername",value = "admin"),
        @WebInitParam(name = "loginPassword",value = "wuzhiyuan5789778"),
        @WebInitParam(name = "resetEnable",value = "true")
})
public class DruidStatViewServlet extends StatViewServlet{
    private static final long serialVersionUID = 1L;
}
