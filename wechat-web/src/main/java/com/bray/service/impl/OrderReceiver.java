package com.bray.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bray.mapper.WyOrderMapper;
import com.bray.mapper.WySafedomainMapper;
import com.bray.model.Vo.OrderModelVo;
import com.bray.model.WyOrder;
import com.bray.model.WyOrderExample;
import com.bray.model.WySafedomain;
import com.bray.service.IOrderWebService;
import com.bray.service.wechat.WechatTemplateMessageServcie;
import com.bray.service.wechat.bean.OrderTemplateKeyParam;
import com.bray.util.DateUtil;
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

/**
 * @Author:wuzhiyuan
 * @Description: rabbit消息队列
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

    @Resource
    private WechatTemplateMessageServcie wechatTemplateMessageServcie;


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
            //发送微信下行通知
            wechatTemplateMessageServcie.sendTemplateMessage(getOrderTemplateKeyParam(orderModelVo));
        } catch (Exception e) {
            log.error("---------email发送错误");
            e.printStackTrace();
        }
    }
    private OrderTemplateKeyParam getOrderTemplateKeyParam(OrderModelVo orderModelVo) {
        OrderTemplateKeyParam orderTemplateKeyParam = new OrderTemplateKeyParam();
        orderTemplateKeyParam.setFirst("来了新的订单啦！ 【" + orderModelVo.getName() + "】");
        orderTemplateKeyParam.setOrderID(orderModelVo.getPhone());
        orderTemplateKeyParam.setOrderMoneySum(orderModelVo.getTotal());
        orderTemplateKeyParam.setBackupFieldName("商品信息:  ");
        orderTemplateKeyParam.setBackupFieldData(DateUtil.formatDate(orderModelVo.getOrderDate(), DateUtil.PATTERN_yyyy_MM_dd_HH_mm) + "\n地址:  " +
                orderModelVo.getProvince() + orderModelVo.getCity() + "\n尺寸:  " + orderModelVo.getSize()+"\n");
        orderTemplateKeyParam.setRemark(StringUtils.isEmpty(orderModelVo.getMessage().trim()) ? "客户未留言!!" : orderModelVo.getMessage());
        return orderTemplateKeyParam;
    }

}
