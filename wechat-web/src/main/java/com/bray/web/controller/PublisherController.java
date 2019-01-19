package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.mapper.WyOrderCopyMapper;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.OrderModelVo;
import com.bray.service.IOrderWebService;
import com.bray.service.impl.OrderSender;
import com.bray.service.impl.OrderServiceImpl;
import com.bray.service.impl.PublisherService;
import com.bray.util.SendEmailUtil;
import io.undertow.io.Sender;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 11:47 2019/1/16
 * @Modified By:
 */
@Controller
@RequestMapping("/mq")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private IOrderWebService iOrderWebService;

    @RequestMapping("order")
    @ResponseBody
    public RestResponseBo sendMessage(OrderModelVo orderModelVo) {
        //插入订单备用表
        iOrderWebService.insertCopy(orderModelVo);
        publisherService.sendMessage(orderModelVo);
        return RestResponseBo.ok();
    }

    @RequestMapping("xiadan")
    @ResponseBody
    public RestResponseBo order(OrderModelVo orderModelVo) {
        //插入订单备用表
        iOrderWebService.insertCopy(orderModelVo);
        // SendEmailUtil.sendEmail(javaMailSender,orderModelVo);
        orderSender.send(JSONObject.toJSONString(orderModelVo));
        return RestResponseBo.ok();
    }
}

