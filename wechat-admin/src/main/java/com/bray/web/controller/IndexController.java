package com.bray.web.controller;

import com.bray.model.WyOrderLog;
import com.bray.service.IOrderAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/home")
    public String home() {
        return "index/index";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model) {
        List<WyOrderLog> wyOrderLogs = iOrderAdminService.queryOrderLogData();
        wyOrderLogs = new ArrayList<WyOrderLog>();
        model.addAttribute("wyOrderLogs",wyOrderLogs);
        model.addAttribute("currentTime",new Date());
        return "index/welcome";
    }
}
