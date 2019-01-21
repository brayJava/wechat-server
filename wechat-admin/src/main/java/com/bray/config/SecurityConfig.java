// package com.bray.config;
//
// import com.bray.handler.LoginfailureHandler;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import javax.annotation.Resource;
// /**
//  * @Author:wuzhiyuan
//  * @Description: 权限控制初始化类
//  * @Date: Created in 1:00 2018/9/29
//  * @Modified By:
//  */
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//     @Resource
//     private LoginfailureHandler loginfailureHandler;
//     /**
//      * http授权规则
//      * @param http
//      * @throws Exception
//      */
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         // super.configure(http);
//
//         http.authorizeRequests().mvcMatchers("/article/**").hasRole("user");
//         //授权不成功，需要跳登入界面
//         http.formLogin().loginPage("/login")
//                 .failureHandler(loginfailureHandler)
//                 .and()
//                 .authorizeRequests()
//                 // .antMatchers("/css/**")
//                 .antMatchers("/login").permitAll()
//                 .anyRequest()
//                 .authenticated()
//                 .and()
//                 .cors().disable();
//     }
//     /**
//      * 登入验证
//      * @param auth
//      * @throws Exception
//      */
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         // super.configure(auth);
//         auth.inMemoryAuthentication().withUser("bray").password("bray").roles("user");
//     }
// }
