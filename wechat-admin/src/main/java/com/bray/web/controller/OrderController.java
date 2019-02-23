package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.aop.cache.RedisPoolCache;
import com.bray.excel.handler.ModuleToExcel;
import com.bray.excel.modules.XFOrderModule;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.OrderDetailVo;
import com.bray.model.Vo.SearchModelVo;
import com.bray.model.WyOrder;
import com.bray.service.IOrderService;
import com.bray.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;
    @Resource
    private RedisPoolCache redisObj;
    @Autowired
    private ApplicationContext applicationContext;
    @RequestMapping("/jump")
    public String jumpOther(Model model) {
        //起始时间为上午00:00:00
        String startDate  = LocalDateTime.of(LocalDate.now(), LocalTime.MIN)
                .format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_yyyy_MM_dd_HH_mm_ss));
        //结束时间为晚上23:59:59
        String endDate = LocalDateTime.of(LocalDate.now(), LocalTime.MAX)
                .format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_yyyy_MM_dd_HH_mm_ss));
        List<WyOrder> wyOrders = (List<WyOrder>) iOrderService.queryAllOrder(startDate, endDate);
        model.addAttribute("wyOrders",wyOrders);
        model.addAttribute("startTime",DateUtil.formatDate(DateUtil.parseDate(startDate),DateUtil.PATTERN_yyyy_MM_dd));
        model.addAttribute("endTime",DateUtil.formatDate(DateUtil.parseDate(endDate),DateUtil.PATTERN_yyyy_MM_dd));
        double money = iOrderService.queryMoney(startDate, endDate);
        model.addAttribute("money",money);
        log.info("-------订单数据为：{}", JSONObject.toJSONString(wyOrders));
        return "order/order-list";
    }
    @RequestMapping("/delete")
    @ResponseBody
    public RestResponseBo delete(Model model,int id) {
        iOrderService.delete(id);
        return RestResponseBo.ok();
    }
    /**
     * 文章列表展示
     * @return
     */
    @PostMapping("/order-search")
    @ResponseBody
    public OrderDetailVo articleSearch(HttpServletRequest request, HttpServletResponse response, SearchModelVo searchModelVo, Model model) {

        List<WyOrder> wyOrders = (List<WyOrder>)iOrderService.queryOrderByCondition(searchModelVo);
        double money = iOrderService.queryMoney(searchModelVo.getStart(), searchModelVo.getEnd());
        model.addAttribute("wyOrders", wyOrders);
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        String showhtml = thymeleafViewResolver.getTemplateEngine().process("order/order-body", ctx);
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setShowHtml(showhtml);
        orderDetailVo.setMoney(money);
        orderDetailVo.setCount(wyOrders.size());
        return orderDetailVo;
    }
    @RequestMapping("/loadfile")
    @ResponseBody
    public RestResponseBo loadfile(String startTime,String endTime) {
        String startDate  = LocalDateTime.of(LocalDate.now(), LocalTime.MIN)
                .format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_yyyyMMdd));
        String excelPath = "order_"+startDate+ Clock.systemDefaultZone().millis();
        //生称excel
        List<XFOrderModule> xfOrderModules = iOrderService.excelList(startTime,endTime);
        try {
            ModuleToExcel.ObjectToExcel(xfOrderModules,XFOrderModule.class,"/home/ftpuser/wechat/excel/"+excelPath+".xlsx");
        } catch (Exception e) {
            log.error("生成excel失败。。。");
            e.printStackTrace();
        }
        RestResponseBo restResponseBo = new RestResponseBo();
        restResponseBo.setMsg(excelPath);
        return restResponseBo;
    }

}
