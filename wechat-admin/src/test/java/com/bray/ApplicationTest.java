package com.bray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 0:05 2018/8/27
 * @Modified By:
 */
@SpringBootApplication
@ComponentScan(value = {"com.bray","com.bray.mapper"})//这个是引用包用的
public class ApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class,args);
    }
}
