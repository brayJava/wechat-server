package com.bray.web.controller;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.service.IArticleService;
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
        // getArticleWithImages(model, articleId);
        return "html/cjh5/cj1/h5";
    }

    /**
     * h5响应式阅读
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/nanjing/{articleId}")
    public String nanjing(HttpServletRequest request, Model model,@PathVariable int articleId) {
        // getArticleWithImages(model, articleId);
        return "html/cjh5/cj3/index";
    }
    /**
     * 快展平台
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/kzh/{articleId}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String yqx(HttpServletRequest request, HttpServletResponse response,Model model,@PathVariable int articleId) {
        String showhtml = getArticleWithImages(model, articleId, "article_h5_2","html/cjh5/cj4/kzh", request, response);
        return showhtml;
    }
    /**
     * 单张图片翻阅式h5
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/tieme/{articleId}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String fanyue(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable int articleId) {
        String showhtml = getArticleWithImages(model, articleId, "article_h5_1","html/cjh5/cj2/index", request, response);
        return showhtml;
    }
    /**
     * 获取图文信息
     * @param model
     * @param articleId
     */
    private String getArticleWithImages(Model model, @PathVariable int articleId,String redisType,String htmlpath,HttpServletRequest request, HttpServletResponse response) {
        ArticleWithImages article = iArticleService.queryCurrentArticle(articleId);

        model.addAttribute("article",article.getWyArticle());
        model.addAttribute("contentHtml",article.getContentHtml());
        model.addAttribute("articleImgs",article.getArticleSubImages());
        if(article.getWyArticle().getIsOrderImg()) {
            if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        }
        //数据迁移
        if(!Objects.isNull(article) && !StringUtils.isEmpty(article.getWyArticle().getDataTransferUrl())) {
            String qyhtml = transferUrl(htmlpath,request, model, response, article);
            return qyhtml;
        }
        //多链数据分流
        if(!Objects.isNull(article) && !StringUtils.isEmpty(article.getWyArticle().getNoShareDomain())) {
            String[] flurl = article.getWyArticle().getNoShareDomain().split(",");
            //随机跳一个url
            int v = (int)Math.floor(Math.random() * flurl.length);
            article.getWyArticle().setDataTransferUrl(flurl[v]);
            return transferUrl(htmlpath,request, model, response, article);
        }
        String showhtml = String.valueOf(redisObj.getRedisValueByKey(redisType+":"+articleId));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process(htmlpath, ctx);
        redisObj.saveDataToRedis(redisType+":"+articleId,showhtml);
        log.info("日志输出：{}",request.getRequestURI().toString());
        return showhtml;
    }

    private String transferUrl(String htmlpath,HttpServletRequest request, Model model, HttpServletResponse response, ArticleWithImages article) {
        model.addAttribute("article", article);
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        return thymeleafViewResolver.getTemplateEngine().process("html/wode/qy", ctx);
    }
}
