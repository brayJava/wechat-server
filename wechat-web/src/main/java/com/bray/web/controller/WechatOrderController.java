package com.bray.web.controller;
import com.alibaba.fastjson.JSONObject;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.OrderModelVo;
import com.bray.service.IOrderWebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Author:wuzhiyuan
 * @Description: 订单相关
 * @Date: Created in 10:49 2018/9/11
 * @Modified By:
 */
@Controller
@Slf4j
@RequestMapping("/order")
public class WechatOrderController {
    @Resource
    private IOrderWebService iOrderWebService;

    @Resource
    private JavaMailSenderImpl javaMailSender;
    /**
     * 订单跳转
     */
    @RequestMapping("/jump-story1")
    String orderStory1() {
        return "order/story1";
    }
    /**
     * 订单跳转
     */
    @RequestMapping("/jump-story")
    String orderStory() {
        return "order/story";
    }
    /**
     * 订单跳转
     */
    @RequestMapping("/jump-order")
    String order() {
        return "order/order";
    }

    @RequestMapping("confirm-order")
    @ResponseBody
    public RestResponseBo orderConfirm(OrderModelVo orderModelVo) {
        iOrderWebService.insert(orderModelVo);
        try {
            //发送email
            sendEmailWithOrder(orderModelVo);
        } catch (Exception e) {
            log.error("---------email发送错误");
            e.printStackTrace();
        }
        log.info("----订单数据...{}", JSONObject.toJSONString(orderModelVo));
        return RestResponseBo.ok();
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
        orderBuf.append("尺寸大小："+orderModelVo.getSize());
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(msg, true,"utf-8");
            helper.setFrom("goodboy_bray@163.com");
            helper.setCc("goodboy_bray@163.com");
            helper.setTo(new String[]{"1318134732@qq.com","937085200@qq.com"});
            helper.setText(orderBuf.toString());
            helper.setSubject("来了新的订单");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setSubject("有新的订单");
//        simpleMailMessage.setText(orderBuf.toString());
//        simpleMailMessage.setTo("1318134732@qq.com");
//        simpleMailMessage.setFrom("goodboy_bray@163.com");
//        Properties prop = new Properties();
//        //解决ip与域名不匹配的问题
//        prop.put("mail.smtp.localhost","mail.163.com");
//        prop.put("mail.smtp.auth", "false");
////        prop.put("mail.smtp.port", "25");
//        javaMailSender.setJavaMailProperties(prop);
        javaMailSender.send(msg);
    }
}