package com.bray.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:wuzhiyuan
 * @Description: memcachesource 存储ip和port
 * @Date: Created in 20:08 2019/2/18
 * @Modified By:
 */
// @Component
// @ConfigurationProperties(prefix = "memcache")
public class MemcacheSource {

    private String ip;

    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
