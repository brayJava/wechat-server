package com.bray.web.controller;

import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.OrderModelVo;
import com.bray.service.impl.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("confirm-order")
    @ResponseBody
    public RestResponseBo sendMessage(OrderModelVo orderModelVo) {
        publisherService.sendMessage(orderModelVo);
        return RestResponseBo.ok();
    }
}

