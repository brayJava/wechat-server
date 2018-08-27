package com.bray.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 23:50 2018/8/24
 * @Modified By:
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
    /**
     * 首页跳转
     */
    @RequestMapping("/share")
    ModelAndView index() {
        return new ModelAndView("html/share","errorCode","00000");
    }
    /**
     * 内容跳转
     */
    @RequestMapping("/content")
    ModelAndView content() {
        return new ModelAndView("html/content","errorCode","00000");
    }
    /**
     * 内容跳转
     */
    @RequestMapping("/story/{id}/{code}")
    ModelAndView story(@PathVariable String id,@PathVariable String code) {
        return new ModelAndView("order/story","errorCode","00000");

    }
    /**
     * 订单跳转
     */
    @RequestMapping("/order")
    ModelAndView order() {
        return new ModelAndView("order/order","errorCode","00000");
    }

}
