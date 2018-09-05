package com.bray.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/home")
    public String home() {
        return "index/index";
    }
}
