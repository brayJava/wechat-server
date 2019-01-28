package com.bray.interceptor;

import com.bray.dto.ConstFinal;
import com.bray.model.WyUser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Author:wuzhiyuan
 * @Description: 拦截器设置
 * @Date: Created in 15:29 2019/1/28
 * @Modified By:
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        Object userObj = session.getAttribute(ConstFinal.USER_NAME);
        if(Objects.isNull(userObj)) {
            response.setStatus(302);
            response.sendRedirect("/to-login");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
