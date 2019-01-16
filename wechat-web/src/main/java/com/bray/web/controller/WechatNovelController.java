package com.bray.web.controller;

import com.bray.aop.cache.RedisPoolCache;
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
     * 小说文案跳转
     */
    @RequestMapping(value="/12345",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpnv(HttpServletRequest request, HttpServletResponse response, Model model) {
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
}
