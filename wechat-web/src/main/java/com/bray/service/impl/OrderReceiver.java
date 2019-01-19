package com.bray.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bray.mapper.WyOrderMapper;
import com.bray.mapper.WySafedomainMapper;
import com.bray.model.Vo.OrderModelVo;
import com.bray.model.WyOrder;
import com.bray.model.WyOrderExample;
import com.bray.model.WySafedomain;
import com.bray.service.IOrderWebService;
import com.bray.util.SendEmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 1:38 2019/1/17
 * @Modified By:
 */
@Component
@RabbitListener(queues = "order")
@Slf4j
public class OrderReceiver {

    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Resource
    private IOrderWebService iOrderWebService;

    @Resource
    private WySafedomainMapper wySafedomainMapper;


    @RabbitHandler
    public void process(String receiverMsg) {

        OrderModelVo orderModelVo = JSONObject.parseObject(receiverMsg, OrderModelVo.class);
        try {
            iOrderWebService.insert(orderModelVo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("插入订单失败...");
        }
        try {
            //发送email
            sendEmailWithOrder(orderModelVo);
        } catch (Exception e) {
            log.error("---------email发送错误");
            e.printStackTrace();
        }
    }
    private void sendEmailWithOrder(OrderModelVo orderModelVo) {
        StringBuilder orderBuf = new StringBuilder();
//        orderBuf.append("------------新的订单------------- \n");
        orderBuf.append("姓名：" + orderModelVo.getName() + "\n");
        orderBuf.append("电话：" + orderModelVo.getPhone() + "\n");
        orderBuf.append("城市：" + orderModelVo.getProvince() + orderModelVo
                .getCity() + orderModelVo.getCounty() + "\n");
        orderBuf.append("地址：" + orderModelVo.getAddress() + "\n");
        orderBuf.append("商品类型：" + orderModelVo.getTitle() + "\n");
        orderBuf.append("尺寸大小：" + orderModelVo.getSize() + "\n");
        if (!StringUtils.isEmpty(orderModelVo.getMessage()))
            orderBuf.append("顾客留言：" + orderModelVo.getMessage());
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(msg, true, "utf-8");
            helper.setFrom("1318134732@qq.com");
            helper.setCc("1318134732@qq.com");
            helper.setTo(new String[]{"78901623@qq.com", "937085200@qq.com", "619105979@qq.com", "527297994@qq.com", "1194633142@qq.com", "1318134732@qq.com"});
            helper.setText(orderBuf.toString().replace("￥",""));
            helper.setSubject("新年前的冲刺!!【" + orderModelVo.getName() + "】");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(msg);
    }

}
