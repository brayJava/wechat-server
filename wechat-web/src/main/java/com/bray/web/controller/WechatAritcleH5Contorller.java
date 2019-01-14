package com.bray.web.controller;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
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

    @Autowired
    private IArticleService iArticleService;


    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Resource
    private RedisPoolCache redisObj;

    @Autowired
    private ApplicationContext applicationContext;
    /**
     * h5响应式阅读
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/h5/{articleId}")
    public String JZFF(HttpServletRequest request, Model model,@PathVariable int articleId) {

        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        model.addAttribute("article",articleWithImages.getWyArticle());
        model.addAttribute("articleImgs",articleWithImages.getArticleSubImages());
        return "html/cjh5/cj1/h5";
    }
}
