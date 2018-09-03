package com.bray.web.controller;

import com.bray.model.Vo.LoginModelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:wuzhiyuan
 * @Description: 登入控制器
 * @Date: Created in 22:29 2018/8/30
 * @Modified By:
 */
@Slf4j
@Controller("/")
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "/login/login";
    }
    /**
     * 用户登入校验
     * @param loginModelVo
     * @return
     */
    @RequestMapping("/verify-login")
    public String login(LoginModelVo loginModelVo, Model model) {
        model.addAttribute("errorCode","error");
        return "/login/login";
    }
}
