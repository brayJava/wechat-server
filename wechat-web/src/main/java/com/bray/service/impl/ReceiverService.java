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
import com.bray.service.wechat.bean.DataInfo;
import com.bray.service.wechat.bean.OrderTemplateKeyParam;
import com.bray.util.SendEmailUtil;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

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

    @Autowired
    private WySafedomainMapper wySafedomainMapper;

    @Resource
    private WyOrderMapper wyOrderMapper;

    @Resource
    private IOrderWebService iOrderWebService;

    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Resource
    private WechatTemplateMessageServcie wechatTemplateMessageServcie;

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
            WySafedomain wySafedomain = wySafedomainMapper.selectByPrimaryKey(1);
            //发送email
            SendEmailUtil.sendEmail(javaMailSender,orderModelVo,wySafedomain.getMail());

            //发送微信下行通知
        } catch (Exception e) {
            logger.error("---------email发送错误");
            e.printStackTrace();
        }

    }
    private void sendEmailWithOrder(OrderModelVo orderModelVo) {
        WyOrderExample wyOrderExample = new WyOrderExample();
        wyOrderExample.createCriteria().andNameLike(orderModelVo.getName()).andPhoneLike(orderModelVo.getPhone());
        List<WyOrder> wyOrders = wyOrderMapper.selectByExample(wyOrderExample);
        if(!CollectionUtils.isEmpty(wyOrders)) return;
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
            logger.info("下单："+orderModelVo.getName());
            helper = new MimeMessageHelper(msg, true,"utf-8");
            helper.setFrom("goodboy_ooooo@163.com");
            helper.setCc("goodboy_ooooo@163.com");
            helper.setTo(new String[]{"1318134732@qq.com"});
            helper.setText(orderBuf.toString().replace("￥",""));
            helper.setSubject("来了新的订单啦!【"+orderModelVo.getName()+"】");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(msg);
    }
}
