package com.bray.web.controller;
import com.alibaba.fastjson.JSONObject;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.OrderModelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        log.info("----订单数据...{}", JSONObject.toJSONString(orderModelVo));
        return RestResponseBo.ok();
    }
}