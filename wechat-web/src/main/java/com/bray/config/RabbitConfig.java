package com.bray.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 1:36 2019/1/17
 * @Modified By:
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue Queue() {
        return new Queue("order");
    }
}
