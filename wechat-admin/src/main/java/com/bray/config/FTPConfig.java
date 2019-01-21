package com.bray.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 11:40 2018/10/9
 * @Modified By:
 */
// @Configuration
// @ConfigurationProperties(prefix = "ftp")
@Data
public class FTPConfig {
    private String host;     //ftp服务器ip
    private int port;        //ftp服务器端口
    private String username; //用户名
    private String password; //密码
    private String basePath; //存放文件的基本路径

}
