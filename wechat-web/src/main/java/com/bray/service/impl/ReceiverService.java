package com.bray.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bray.mapper.WyOrderMapper;
import com.bray.model.Vo.OrderModelVo;
import com.bray.service.IOrderWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 11:47 2019/1/16
 * @Modified By:
 */
@Component
public class ReceiverService  implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(ReceiverService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private IOrderWebService iOrderWebService;

    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer<String> valueSerializer = redisTemplate.getStringSerializer();
        String deserialize = valueSerializer.deserialize(message.getBody());
        logger.info("收到订单数据" + deserialize);
        OrderModelVo orderModelVo = JSONObject.parseObject(deserialize, OrderModelVo.class);
        try {
            iOrderWebService.insert(orderModelVo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("插入订单失败...");
        }
        try {
            //发送email
            sendEmailWithOrder(orderModelVo);
        } catch (Exception e) {
            logger.error("---------email发送错误");
            e.printStackTrace();
        }

    }
    private void sendEmailWithOrder(OrderModelVo orderModelVo) {
        StringBuilder orderBuf = new StringBuilder();
//        orderBuf.append("------------新的订单------------- \n");
        orderBuf.append("姓名："+orderModelVo.getName()+"\n");
        orderBuf.append("电话："+orderModelVo.getPhone()+"\n");
        orderBuf.append("城市："+orderModelVo.getProvince()+orderModelVo
                .getCity()+orderModelVo.getCounty()+"\n");
        orderBuf.append("地址："+orderModelVo.getAddress()+"\n");
        orderBuf.append("商品类型："+orderModelVo.getTitle()+"\n");
        orderBuf.append("尺寸大小："+orderModelVo.getSize()+"\n");
        if(!StringUtils.isEmpty(orderModelVo.getMessage()))
            orderBuf.append("顾客留言："+orderModelVo.getMessage());
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(msg, true,"utf-8");
            helper.setFrom("goodboy_bray@163.com");
            helper.setCc("goodboy_bray@163.com");
            helper.setTo(new String[]{"78901623@qq.com","937085200@qq.com","619105979@qq.com","527297994@qq.com","1194633142@qq.com","1318134732@qq.com"});
            helper.setText(orderBuf.toString());
            helper.setSubject("来了新的订单啦!【"+orderModelVo.getName()+"】");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(msg);
    }
}
