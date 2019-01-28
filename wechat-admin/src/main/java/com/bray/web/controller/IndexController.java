package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.aop.cache.RedisPoolCache;
import com.bray.dto.ConstFinal;
import com.bray.model.Vo.OrderModelVo;
import com.bray.model.WyOrderLog;
import com.bray.model.WyUser;
import com.bray.service.IOrderAdminService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author:wuzhiyuan
 * @Description: 主页相关跳转
 * @Date: Created in 11:23 2018/8/31
 * @Modified By:
 */
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {

    @Resource
    private IOrderAdminService iOrderAdminService;

    @Resource
    private RedisPoolCache redisObj;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/wzylm")
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        WyUser wyUser = (WyUser)session.getAttribute(ConstFinal.USER_NAME);
        model.addAttribute("username",wyUser.getUsername());
        return "index/index";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model) {
        List<WyOrderLog> wyOrderLogs = iOrderAdminService.queryOrderLogData();
        List<String> list = redisObj.lrangeRedis("orderlist", 0, 10);
        List<OrderModelVo> orderlist = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)) {
            list.stream().forEach(x ->{
                OrderModelVo orderModelVo = JSONObject.parseObject(x, OrderModelVo.class);
                if(Objects.isNull(orderModelVo.getOrderDate())) {
                    orderModelVo.setOrderDate(new Date());
                }
                orderlist.add(orderModelVo);
            });
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        //获取手机型号
        List<String> fromAndroidList = redisObj.lrangeRedis("fromAndroid", 0, 30);
        List<String> fromIphoneList = redisObj.lrangeRedis("fromIphone", 0, 10);
        List<String> fromOtherList = redisObj.lrangeRedis("fromOther", 0, 10);
        List<String> sizelAndroid = redisObj.lrangeRedis("fromAndroid", 0,1000000000);
        List<String> sizelIphone = redisObj.lrangeRedis("fromIphone", 0,1000000000);
        List<String> sizelOther = redisObj.lrangeRedis("fromOther", 0,1000000000);
        model.addAttribute("percentAndroid",numberFormat.format((float)Integer.valueOf(sizelAndroid.size()) / (float)(sizelAndroid.size()+sizelIphone.size()+sizelOther.size())*100));
        model.addAttribute("percentIos",numberFormat.format((float)Integer.valueOf(sizelIphone.size()) / (float)(sizelAndroid.size()+sizelIphone.size()+sizelOther.size())*100));
        model.addAttribute("percentOther",numberFormat.format((float)Integer.valueOf(sizelOther.size()) / (float)(sizelAndroid.size()+sizelIphone.size()+sizelOther.size())*100));
        model.addAttribute("totalVisit",sizelAndroid.size()+sizelIphone.size());
        model.addAttribute("fromAndroidList",fromAndroidList);
        model.addAttribute("fromIphoneList",fromIphoneList);
        model.addAttribute("fromOtherList",fromOtherList);
        model.addAttribute("wyOrderLogs",wyOrderLogs);
        model.addAttribute("currentTime",new Date());
        model.addAttribute("orderlist",orderlist);
        return "index/welcome";
    }
    /**
     * 订单日志查询
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="/ssckOrder",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String ssckOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<String> list = redisObj.lrangeRedis("orderlist", 0, 20);
        List<OrderModelVo> orderModelVos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)) {
            list.stream().forEach(x ->{
                OrderModelVo y = JSONObject.parseObject(x, OrderModelVo.class);
                if(Objects.isNull(y .getOrderDate())) {
                    y.setOrderDate(new Date());
                }
                orderModelVos.add(y);
            });
        }
        model.addAttribute("orderlist",orderModelVos);
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        String html = thymeleafViewResolver.getTemplateEngine().process("order/order-log", ctx);
        return html;
    }

}
