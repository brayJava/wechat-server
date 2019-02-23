package com.bray.web.controller;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.util.HttpRequestDeviceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:wuzhiyuan
 * @Description: 小说相关
 * @Date: Created in 0:46 2019/1/17
 * @Modified By:
 */
@Controller
@Slf4j
@RequestMapping("/novel")
public class WechatNovelController {

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Resource
    private RedisPoolCache redisObj;

    @Autowired
    private ApplicationContext applicationContext;
    /**
     * 12345小说文案跳转
     */
    @RequestMapping(value="/12345",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpnv(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("novel:12345"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("novel/12345", ctx);
        redisObj.saveDataToRedis("novel:12345",showhtml);
        return showhtml;
    }
    /**
     * 12345小说文案跳转-无返回
     */
    @RequestMapping(value="/nofh-12345",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpNoHFnv(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("novel:nofh-12345"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("novel/nofh-12345", ctx);
        redisObj.saveDataToRedis("novel:nofh-12345",showhtml);
        return showhtml;
    }
    /**
     * jdqb小说文案跳转
     */
    @RequestMapping(value="/jdqb",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpjdqb(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("novel:jdqb"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("novel/jdqb", ctx);
        redisObj.saveDataToRedis("novel:jdqb",showhtml);
        return showhtml;
    }
    /**
     * jdqb小说文案跳转-无返回
     */
    @RequestMapping(value="/nofh-jdqb",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpNoFHjdqb(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("novel:nofh-jdqb"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("novel/nofh-jdqb", ctx);
        redisObj.saveDataToRedis("novel:nofh-jdqb",showhtml);
        return showhtml;
    }
    /**
     * 最佳女婿小说文案跳转
     */
    @RequestMapping(value="/zjnx",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpzjnx(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("novel:zjnx"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("novel/zjnx", ctx);
        redisObj.saveDataToRedis("novel:zjnx",showhtml);
        return showhtml;
    }
    /**
     * 最佳女婿小说文案跳转-无返回
     */
    @RequestMapping(value="/nofh-zjnx",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpNoFHzjnx(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("novel:nofh-zjnx"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("novel/nofh-zjnx", ctx);
        redisObj.saveDataToRedis("novel:nofh-zjnx",showhtml);
        return showhtml;
    }
    /**
     * 终极兵神小说文案跳转
     */
    @RequestMapping(value="/zjbs",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpzjbs(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("novel:zjbs"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("novel/zjbs", ctx);
        redisObj.saveDataToRedis("novel:zjbs",showhtml);
        return showhtml;
    }
    /**
     * 终极兵神小说文案跳转-无返回
     */
    @RequestMapping(value="/nofh-zjbs",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpNoFHzjbs(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("novel:nofh-zjbs"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("novel/nofh-zjbs", ctx);
        redisObj.saveDataToRedis("novel:nofh-zjbs",showhtml);
        return showhtml;
    }
    /**
     * 古术医修小说文案跳转
     */
    @RequestMapping(value="/gsyx",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpgsyx(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("novel:gsyx"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("novel/gsyx", ctx);
        redisObj.saveDataToRedis("novel:gsyx",showhtml);
        return showhtml;
    }
    /**
     * 古术医修小说文案跳转-无返回
     */
    @RequestMapping(value="/nofh-gsyx",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpNoFHgsyx(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("novel:nofh-gsyx"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("novel/nofh-gsyx", ctx);
        redisObj.saveDataToRedis("novel:nofh-gsyx",showhtml);
        return showhtml;
    }
}
