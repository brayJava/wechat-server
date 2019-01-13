package com.bray.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author:wuzhiyuan
 * @Description: h5页面
 * @Date: Created in 14:05 2018/11/2
 * @Modified By:
 */
@RequestMapping("/hcj")
@Controller
@Slf4j
public class WechatAritcleH5Contorller {
    /**
     * h5响应式阅读
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/h5")
    public String JZFF(HttpServletRequest request, Model model) {
        return "html/cjh5/cj1/index";
    }
}
