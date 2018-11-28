package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.aop.cache.RedisPoolCache;
import com.bray.config.WebConst;
import com.bray.dto.ConstFinal;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.Vo.ArticleNewImages;
import com.bray.model.WySubdomain;
import com.bray.service.IArticleService;
import com.bray.service.IDomainWebService;
import com.bray.util.Base64Util;
import com.bray.util.HttpRequestDeviceUtils;
import com.bray.util.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.util.*;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 17:12 2018/11/20
 * @Modified By:
 */
@RequestMapping("/test")
@Controller
@Slf4j
public class testController
{

    @Autowired
    private IArticleService iArticleService;

    @Resource
    private IDomainWebService iDomainWebService;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Resource
    private RedisPoolCache redisObj;

    @Autowired
    private ApplicationContext applicationContext;

    /***************************一张张加载图片分享start*******************************/
    /**
     * 新防风界面带封面2
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/jump-xwwd/{articleId}")
    public ModelAndView test(HttpServletRequest request, Model model, @PathVariable String articleId) {
        // if (!HttpRequestDeviceUtils.isMobileDevice(request))
        //     return new ModelAndView("redirect:http://www.pinduoduo.com");
        //获取域名集合map
        WySubdomain wySubdomain = getWySubdomain(articleId, WebConst.SUB_COMMON_DOMAIN);
        String fftime = Clock.systemDefaultZone().millis() + "";
        String encodeTime = Base64Util.encode(fftime);
        String domain = WechatUtil.getRandomChar() + "." + wySubdomain.getSubDomain();
        domain = "localhost:8081";
        return new ModelAndView("redirect:http://" + domain + "/test/" + articleId +"?id="+encodeTime+"&gress=12306#cf6ad8d9c8244629d29463e67b4ae0f5");
    }
    /**
     * 新防封内容展示 (落地页)
     *
     * @param articleId 文章id
     * @return
     */
    @RequestMapping("/{articleId}")
    public ModelAndView realFangFengCon(HttpServletRequest request, Model model, @PathVariable String articleId) {
        ModelAndView modelAndView = new ModelAndView();
        // if (!HttpRequestDeviceUtils.isMobileDevice(request))
        //     return new ModelAndView("redirect:http://www.pinduoduo.com");
        ArticleNewImages articleNewImages = iArticleService.queryNewArticleImages(articleId);
        // if(!Objects.isNull(articleNewImages) && !StringUtils.isEmpty(articleNewImages.getData().getDataTransferUrl()))
        //     return new ModelAndView("redirect:"+articleNewImages.getData().getDataTransferUrl());
        //判断是否开启防封记录，如何开启，则打开记录防封
        if(articleNewImages.getData().isImgnews_forcedShare() && redislogff(request)) {
            return new ModelAndView("redirect:http://"+WechatUtil.getRandomChar()+".189nfds.cn/cc");
        }
        String encode = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        WySubdomain wySubdomain = getWySubdomain(articleId,WebConst.SUB_SHARE_DOMAIN);
        model.addAttribute("newarticle", Base64Util.encode(JSONObject.toJSONString(articleNewImages)));
        model.addAttribute("domainUrl",getDomainName(wySubdomain.getSubDomain()));
        model.addAttribute("shareUrl","http://"+WechatUtil.getRandomChar()+"."+wySubdomain.getSubDomain()+"/test/jump-xwwd/"+articleId+"?"+encode);
        model.addAttribute("article", articleNewImages);
        modelAndView.setViewName("html/newff2/jiazaiHtml");
        return modelAndView;
    }
    /***************************一张张加载图片分享end*******************************/

    /***************************一张雪平台加载图片分享start***************************/
    /**
     * 新防风界面带封面2
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/jump/{articleId}")
    public ModelAndView shareFangfang(HttpServletRequest request, Model model,@PathVariable String articleId) {
        ModelAndView modelAndView = new ModelAndView();
        // if (!HttpRequestDeviceUtils.isMobileDevice(request))
        //     return new ModelAndView("redirect:http://www.pinduoduo.com");
        //获取域名集合map
        WySubdomain wySubdomain = getWySubdomain(articleId,WebConst.SUB_COMMON_DOMAIN);
        String fftime = Clock.systemDefaultZone().millis() + "";
        String encodeTime = Base64Util.encode(fftime);
        String domain = WechatUtil.getRandomChar() + "." + wySubdomain.getSubDomain();
        domain = "localhost:8081";
        return new ModelAndView("redirect:http://"+domain+"/test/content/"+articleId+"?id="+encodeTime);
    }
    /**
     * 新防风界面带封面2 (落地页)
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/content/{articleId}")
    public ModelAndView shareConent(HttpServletRequest request, Model model,@PathVariable String articleId) {
        ModelAndView modelAndView = new ModelAndView();
        // if ( !HttpRequestDeviceUtils.isMobileDevice(request) )
        //     return new ModelAndView("redirect:http://www.pinduoduo.com");
        WySubdomain wySubdomain = getWySubdomain(articleId,WebConst.SUB_SHARE_DOMAIN);
        String encodeTime = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        //判断是否开启防封记录，如何开启，则打开记录防封
        if(articleWithImages.getWyArticle().getForcedShare() && redislogff(request)) {
            return new ModelAndView("redirect:http://"+WechatUtil.getRandomChar()+".189nfds.cn/cc");
        }
        modelAndView.setViewName("html/fmff/content");
        model.addAttribute("article",articleWithImages);
        model.addAttribute("domainUrl",getDomainName(wySubdomain.getSubDomain()));
        model.addAttribute("shareUrl","http://"+WechatUtil.getRandomChar()+"."+wySubdomain.getSubDomain()+"/test/jump/"+articleId+"?"+encodeTime);
        return modelAndView;
    }
    /***************************一张雪平台加载图片分享end*******************************/

    /***************************万铭公司平台加载图片分享start*******************************/
    /**
     * 新防风界面带封面2
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/jump-wx/{articleId}")
    public ModelAndView wmpt(HttpServletRequest request, Model model,@PathVariable String articleId) {
        ModelAndView modelAndView = new ModelAndView();
        // if (!HttpRequestDeviceUtils.isMobileDevice(request))
        //     return new ModelAndView("redirect:http://www.pinduoduo.com");
        //获取域名集合map
        WySubdomain wySubdomain = getWySubdomain(articleId,WebConst.SUB_COMMON_DOMAIN);
        String fftime = Clock.systemDefaultZone().millis() + "";
        String encodeTime = Base64Util.encode(fftime);
        String domain = WechatUtil.getRandomChar() + "." + wySubdomain.getSubDomain();
        domain = "localhost:8081";
        return new ModelAndView("redirect:http://"+domain+"/test/new/zsff/"+articleId+"?id="+encodeTime+"&cid="+articleId);
    }
    /**
     * 新防封界面 (落地页)
     * @param request
     * @return
     */
    @RequestMapping("new/zsff/{articleId}")
    public ModelAndView realFangFeng(HttpServletRequest request,@PathVariable String articleId) {
        ModelAndView modelAndView = new ModelAndView();
        // if(!HttpRequestDeviceUtils.isMobileDevice(request)) return new ModelAndView("redirect:http://www.pinduoduo.com");
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        //         //判断是否开启防封记录，如何开启，则打开记录防封
        if(articleWithImages.getWyArticle().getForcedShare() && redislogff(request)) {
            return new ModelAndView("redirect:http://"+WechatUtil.getRandomChar()+".189nfds.cn/cc");
        }
        modelAndView.setViewName("html/cyff/newzsff");
        return modelAndView;
    }
    /**
     * 新防封内容展示
     * @param request
     * @param cid     文章id
     * @return
     */
    @RequestMapping("/content")
    @ResponseBody
    public ArticleWithImages realFangFengCon(HttpServletRequest request, HttpServletResponse response, Model model, String cid) {
        //获取图片相关信息
        ArticleWithImages article = iArticleService.queryCurrentArticle(cid);
        //取缓存
        String html = String.valueOf(redisObj.getRedisValueByKey("images_list:"+cid));
        if(StringUtils.isEmpty(html) || "null".equals(html)) {
            model.addAttribute("article", article);
            //手动渲染
            SpringWebContext ctx = new SpringWebContext(request,response,
                    request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
            html = thymeleafViewResolver.getTemplateEngine().process("images_list", ctx);
            redisObj.saveDataToRedis("images_list:"+cid,html);
        }
        article.setContentHtml(html);
        String encodeTime = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        WySubdomain wySubdomain = getWySubdomain(cid,WebConst.SUB_SHARE_DOMAIN);
        article.setDomainUrl(getDomainName(wySubdomain.getSubDomain()));
        article.setShareUrl("http://"+WechatUtil.getRandomChar()+"."+wySubdomain.getSubDomain()+"/test/jump-wx/"+cid+"?"+encodeTime);
        log.info("日志输出：{}",request.getRequestURI().toString());
        return article;
    }
    /***************************万铭公司平台加载图片分享end*******************************/
    /**
     * 新防风界面带封面3
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/newff/{articleId}")
    public ModelAndView newFangFeng(HttpServletRequest request, Model model, HttpServletResponse response, @PathVariable String articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!HttpRequestDeviceUtils.isMobileDevice(request))
            return new ModelAndView("redirect:http://www.pinduoduo.com");
        //获取图片相关信息
        ArticleWithImages article = iArticleService.queryCurrentArticle(articleId);
        //取缓存
        String html = String.valueOf(redisObj.getRedisValueByKey("images_content:"+articleId));
        if(StringUtils.isEmpty(html) || "null".equals(html)) {
            model.addAttribute("article", article);
            log.info("返回连接：{}",article.getWyArticle().getGobackUrl());
            //手动渲染
            SpringWebContext ctx = new SpringWebContext(request,response,
                    request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
            html = thymeleafViewResolver.getTemplateEngine().process("html/wode/image_content", ctx);
            redisObj.saveDataToRedis("images_content:"+articleId,html);
        }
        article.setContentHtml(html);
        log.info("日志输出：{}",request.getRequestURI().toString());
        modelAndView.addObject("article",article);
        modelAndView.setViewName("html/wode/mylove");
        return modelAndView;
    }
    /**
     * 获取域名
     * @param articleId
     * @return
     */
    private WySubdomain getWySubdomain(@PathVariable String articleId,String domainType) {
        //获取域名集合map
        Map<String,Object> domainMap = (HashMap<String,Object>)
                iDomainWebService.queryDomainByredisServer(getDomainFlag(articleId),articleId);
        List<WySubdomain> wySubdomains = (List<WySubdomain>) domainMap.get(domainType);
        WySubdomain wySubdomain = new WySubdomain();
        if(!StringUtils.isEmpty(wySubdomains)) {
            wySubdomain = wySubdomains.get(0);
        } else {
            wySubdomain.setSubDomain("www.pinduoduo.com");
        }
        return wySubdomain;
    }

    /**
     * 获取域名名称（如为子域名，则截取主域名）
     * @param subdomain
     * @return
     */
    private String getDomainName(String subdomain) {
        String domain = String.valueOf(subdomain);
        if(!StringUtils.isEmpty(domain)) {
            domain = domain.substring(domain.indexOf(".")+1, domain.length());
        }
        return domain;
    }
    /**
     * 获取域名map集合值
     * @param articleId
     * @return
     */
    private String getDomainFlag(@PathVariable String articleId) {
        return ConstFinal.DOMAIN_MAP+"_"+articleId;
    }
    /**
     * 是否添加了防封记录 ，存在就打不开
     * @param request
     * @return
     */
    private boolean redislogff(HttpServletRequest request) {
        boolean islog = false;
        /******防封缓存界面只能访问一次，再次访问如果存在则打开失败**********/
        String requestURI = request.getRequestURI();
        String contentUrlCache = requestURI.substring(requestURI.lastIndexOf("/") + 1, requestURI.length());
        Map<String, String[]> parameterMap = request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
        String encode = "";
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> next = iterator.next();
            String key = next.getKey();
            if("id".equals(key)) {
                encode = String.valueOf(next.getValue()[0]);
            }
        }
        String existHtmlUrl = iArticleService.queryExistHtmlUrl(contentUrlCache+encode);
        log.info("访问链接：{}，访问时间戳：{}",requestURI,existHtmlUrl);
        if(!StringUtils.isEmpty(existHtmlUrl)) {
            islog = true;
        } else {
            iArticleService.insertHtmlUrlToRedis(contentUrlCache+encode);
        }
        /******************防封缓存界面只能访问一次，end***************/
        return islog;
    }
}
