package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.WyOrder;
import com.bray.service.IOrderService;
import com.bray.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 订单控制器
 * @Date: Created in 12:55 2018/9/19
 * @Modified By:
 */
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private IOrderService iOrderService;

    @RequestMapping("/jump")
    public String jumpOther(Model model) {
        String dateStr = DateUtil.formatDate(new Date(), DateUtil.PATTERN_yyyy_MM_dd);
        String startDate = DateUtil.getStartDate(dateStr);
        String endDate = DateUtil.getEndDate(dateStr);
        List<WyOrder> wyOrders = (List<WyOrder>) iOrderService.queryAllOrder(startDate, endDate);
        model.addAttribute("wyOrders",wyOrders);
        log.info("-------订单数据为：{}", JSONObject.toJSONString(wyOrders));
        return "order/order-list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RestResponseBo delete(Model model,int id) {
        iOrderService.delete(id);
        return RestResponseBo.ok();
    }
}
