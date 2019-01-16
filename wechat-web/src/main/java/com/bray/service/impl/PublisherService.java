package com.bray.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bray.model.Vo.OrderModelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 11:46 2019/1/16
 * @Modified By:
 */
@Service
public class PublisherService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String sendMessage(OrderModelVo orderModelVo) {
        try {
            redisTemplate.convertAndSend("TOPIC_USERNAME", JSONObject.toJSONString(orderModelVo));
            return "订单发送成功了";

        } catch (Exception e) {
            e.printStackTrace();
            return "订单发送失败了";
        }
    }
}
