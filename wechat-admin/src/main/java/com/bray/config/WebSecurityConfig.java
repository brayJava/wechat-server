// package com.bray.config;
//
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.UserDetailsService;
//
// /**
//  * @Author:wuzhiyuan
//  * @Description: 权限相关配置类
//  * @Date: Created in 12:08 2018/11/10
//  * @Modified By:
//  */
// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
// //使用@EnableGlobalMethodSecurity(prePostEnabled = true)
// // 这个注解，可以开启security的注解，我们可以在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解。
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//
//     @Override
//     protected UserDetailsService userDetailsService() {
//         return super.userDetailsService();
//     }
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.csrf().disable();
//         http.authorizeRequests()
//                 .antMatchers("/index").permitAll()
//                 .antMatchers("/css/**",
//                     "/fonts/**",
//                     "/images/**",
//                     "/js/**",
//                     "/lib/**",
//                     "/error/**")
//                 .permitAll()
//                 .anyRequest().authenticated().and()
//              .formLogin().loginPage("/login")// url请求路径
//                 .defaultSuccessUrl("/index/home").permitAll().and()
//                 .logout().permitAll();
//
//         http.logout().logoutSuccessUrl("/");
//     }
//
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.inMemoryAuthentication()
//                 .withUser("root")
//                 .password("root")
//                 .roles("ADMIN","USER")
//                 .and()
//                 .withUser("admin").password("admin")
//                 .roles("ADMIN", "USER")
//                 .and()
//                 .withUser("user").password("user")
//                 .roles("USER");
//     }
// }
