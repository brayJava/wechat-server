package com.bray.web.controller;
import com.alibaba.fastjson.JSONObject;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.OrderModelVo;
import com.bray.service.IOrderWebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
        log.info("----订单数据...{}", JSONObject.toJSONString(orderModelVo));
        return RestResponseBo.ok();
    }
}