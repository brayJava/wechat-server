package com.bray.service.impl;

import com.bray.model.Vo.OrderModelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 1:37 2019/1/17
 * @Modified By:
 */
@Component
@Slf4j
public class OrderSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String sendmsg) {
        log.info("发送信息："+sendmsg);
        this.rabbitTemplate.convertAndSend("order", sendmsg);
    }
}
