package com.bray.web.controller;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.service.IArticleService;
import com.bray.service.IDomainWebService;
import com.bray.util.HttpRequestDeviceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Author:wuzhiyuan
 * @Description: 寻求稳定的文章
 * @Date: Created in 0:43 2019/3/2
 * @Modified By:
 */
@RequestMapping("/wd")
@Controller
@Slf4j
public class WechatWDController {

    @Autowired
    private IArticleService iArticleService;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Resource
    private RedisPoolCache redisObj;

    @Autowired
    private ApplicationContext applicationContext;
    /**
     * 新填稳定wd-1
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/abc/{articleId}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String wd(HttpServletRequest request, Model model, HttpServletResponse response, @PathVariable int articleId) {

        ArticleWithImages article = iArticleService.queryCurrentArticle(articleId);
        if(article.getWyArticle().getIsOrderImg()) {
            if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        }
        if(!Objects.isNull(article) && !StringUtils.isEmpty(article.getWyArticle().getDataTransferUrl())) {
            String qyhtml = transferUrl(request, model, response, article);
            return qyhtml;
        }
        //多链数据分流
        if(!Objects.isNull(article) && !StringUtils.isEmpty(article.getWyArticle().getNoShareDomain())) {
            String[] flurl = article.getWyArticle().getNoShareDomain().split(",");
            //随机跳一个url
            int v = (int)Math.floor(Math.random() * flurl.length);
            article.getWyArticle().setDataTransferUrl(flurl[v]);
            return transferUrl(request, model, response, article);
        }
        //1.从redis缓存中查询
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("wd_1_article:"+articleId));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //取缓存
        String html = String.valueOf(redisObj.getRedisValueByKey("wd_1_content:"+articleId));
        if(StringUtils.isEmpty(html) || "null".equals(html)) {
            model.addAttribute("article", article);
            log.info("返回连接：{}",article.getWyArticle().getGobackUrl());
            //手动渲染
            SpringWebContext ctx = new SpringWebContext(request,response,
                    request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
            html = thymeleafViewResolver.getTemplateEngine().process("html/wd-1/image_content", ctx);
            redisObj.saveDataToRedis("wd_1_content:"+articleId,html);
        }
        article.setContentHtml(html);
        model.addAttribute("article", article);
        //手动渲染原文
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("html/wd-1/index", ctx);
        redisObj.saveDataToRedis("wd_1_article:"+articleId,showhtml);
        log.info("日志输出：{}",request.getRequestURI().toString());
        return showhtml;
    }
    //数据迁移
    private String transferUrl(HttpServletRequest request, Model model, HttpServletResponse response, ArticleWithImages article) {
        model.addAttribute("article", article);
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        return thymeleafViewResolver.getTemplateEngine().process("html/wode/qy", ctx);
    }
}
