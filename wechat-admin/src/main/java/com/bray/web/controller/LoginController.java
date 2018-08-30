package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.web.model.Vo.LoginModelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/verify-login")
    @ResponseBody
    public JSONObject login(LoginModelVo loginModelVo,Model model) {

//        if("wzy".equals(loginModelVo.getUsername()) && "wzy".equals(loginModelVo.getPassword())) {
//            return "SUCCESS";
//        }
        model.addAttribute("errorCode","error");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errorCode","error");
        return jsonObject;
    }

}
