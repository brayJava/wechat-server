package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.aop.cache.RedisPoolCache;
import com.bray.model.Vo.OrderModelVo;
import com.bray.model.WyOrderLog;
import com.bray.service.IOrderAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @RequestMapping("/wzylm")
    public String home() {
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
                orderlist.add(orderModelVo);
            });
        }
        model.addAttribute("wyOrderLogs",wyOrderLogs);
        model.addAttribute("currentTime",new Date());
        model.addAttribute("orderlist",orderlist);
        return "index/welcome";
    }
}
