// package com.bray.handler;
//
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
// import org.springframework.stereotype.Component;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
// /**
//  * @Author:wuzhiyuan
//  * @Description: 登入失败操作
//  * @Date: Created in 2:06 2018/9/29
//  * @Modified By:
//  */
// @Component
// public class LoginfailureHandler extends SimpleUrlAuthenticationFailureHandler{
//     //登入失败提示
//     public  LoginfailureHandler(){
//         this.setDefaultFailureUrl("/login?error=true");
//     }
//
//     @Override
//     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//         super.onAuthenticationFailure(request, response, exception);
//     }
// }
