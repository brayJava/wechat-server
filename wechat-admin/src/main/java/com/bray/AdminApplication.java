package com.bray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.annotation.WebListener;

/**
 * @Author:wuzhiyuan
 * @Description: springboot启动类
 * @Date: Created in 21:50 2018/8/30
 * @Modified By:
 */
@SpringBootApplication
@WebListener
@ServletComponentScan(basePackages = {"com.bray.web.filter"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}