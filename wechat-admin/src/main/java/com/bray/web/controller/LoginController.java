package com.bray.web.controller;

import com.bray.dto.ConstFinal;
import com.bray.dto.ConstatFinal;
import com.bray.model.Vo.LoginModelVo;
import com.bray.model.WyUser;
import com.bray.service.IUserAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 登入控制器
 * @Date: Created in 22:29 2018/8/30
 * @Modified By:
 */
@Slf4j
@Controller("/")
public class LoginController {

    @Resource
    private IUserAdminService iUserAdminService;

    @RequestMapping("/to-login")
    public String login() {
        return "login/login";
    }

    /**
     * 登入校验
     * @param loginModelVo
     * @param model
     * @param httpSession
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(LoginModelVo loginModelVo, Model model, HttpSession httpSession) {

        if(!StringUtils.isEmpty(loginModelVo.getUsername()) && !StringUtils.isEmpty(loginModelVo.getPassword())) {
            List<WyUser> wyUsers = iUserAdminService.login(loginModelVo);
            if(!CollectionUtils.isEmpty(wyUsers)) {
                httpSession.setAttribute(ConstFinal.USER_NAME,wyUsers.get(0));
                return new ModelAndView("redirect:/index/wzylm");
            }
        }
        return new ModelAndView("login/login","errorcode","001");
    }

    @RequestMapping("/loginout")
    public String loginout(HttpSession httpSession) {
        //登出删除session
        httpSession.removeAttribute(ConstFinal.USER_NAME);
        return "login/login";
    }
    /**
     * 用户登入校验
     * @param loginModelVo
     * @return
     */
    @RequestMapping("/verify-login")
    public String login1(LoginModelVo loginModelVo, Model model) {
        model.addAttribute("errorCode","error");
        return "login/login";
    }


}
