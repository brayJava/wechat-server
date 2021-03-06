package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.aop.cache.RedisPoolCache;
import com.bray.config.WebConst;
import com.bray.dto.ConstFinal;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.Vo.ArticleNewImages;
import com.bray.model.WyArticle;
import com.bray.model.WySubdomain;
import com.bray.service.IArticleService;
import com.bray.service.IDomainWebService;
import com.bray.util.Base64Util;
import com.bray.util.HttpRequestDeviceUtils;
import com.bray.util.MobileUtil;
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
 * @Date: Created in 14:05 2018/11/2
 * @Modified By:
 */
@RequestMapping("/1111")
@Controller
@Slf4j
public class WechatAritcleController {

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
    public ModelAndView JZFF(HttpServletRequest request, Model model,@PathVariable int articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!HttpRequestDeviceUtils.isMobileDevice(request))
            return new ModelAndView("redirect:http://www.baidu.com");
        ArticleNewImages articleNewImages = iArticleService.queryNewArticleImages(articleId);
        if(!Objects.isNull(articleNewImages) && !StringUtils.isEmpty(articleNewImages.getData().getDataTransferUrl()))
            return new ModelAndView("redirect:"+articleNewImages.getData().getDataTransferUrl());
        //获取域名集合map
        WySubdomain wySubdomain = getWySubdomain(articleId, WebConst.SUB_COMMON_DOMAIN);
        String encodeTime = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        return new ModelAndView("redirect:http://" + WechatUtil.getRandomChar()+"."+ wySubdomain.getSubDomain() + "/jzff/" + articleId + "?id="+encodeTime+"&gress=12306#cf6ad8d9c8244629d29463e67b4ae0f5");
    }
    /**
     * 新防封内容展示
     *
     * @param articleId 文章id
     * @return
     */
    // @RequestMapping("/{articleId}")
    @RequestMapping("/7777/{articleId}")
    public ModelAndView realFangFengCon(HttpServletRequest request, Model model, @PathVariable int articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!HttpRequestDeviceUtils.isMobileDevice(request))
            return new ModelAndView("redirect:http://www.baidu.com");
        ArticleNewImages articleNewImages = iArticleService.queryNewArticleImages(articleId);
        //shuju qinayi
        if(!Objects.isNull(articleNewImages) && !StringUtils.isEmpty(articleNewImages.getData().getDataTransferUrl()))
            return new ModelAndView("redirect:"+articleNewImages.getData().getDataTransferUrl());
        //判断是否开启防封记录，如何开启，则打开记录防封
        if(articleNewImages.getData().isImgnews_forcedShare() && redislogff(request)) {
            return new ModelAndView("redirect:http://"+WechatUtil.getRandomChar()+".hdisahfo.cn/54395/rew");
        }
        String encodeTime = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        WySubdomain wySubdomain = getWySubdomain(articleId,WebConst.SUB_SHARE_DOMAIN);
        model.addAttribute("newarticle", Base64Util.encode(JSONObject.toJSONString(articleNewImages)));
        if(articleNewImages.getData().isImgnews_forcedShare()) {
            model.addAttribute("domainUrl",getDomainName(wySubdomain.getSubDomain()));
            model.addAttribute("shareUrl","http://"+WechatUtil.getRandomChar()+"."+wySubdomain.getSubDomain()+"/1111/jump-xwwd/"+articleId+"?"+encodeTime);
        }
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
    public ModelAndView shareFangfang(HttpServletRequest request, Model model,@PathVariable int articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!HttpRequestDeviceUtils.isMobileDevice(request))
            return new ModelAndView("redirect:http://www.baidu.com");
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        if(!Objects.isNull(articleWithImages) && !StringUtils.isEmpty(articleWithImages.getWyArticle().getDataTransferUrl()))
            return new ModelAndView("redirect:"+articleWithImages.getWyArticle().getDataTransferUrl());
        //获取域名集合map
        WySubdomain wySubdomain = getWySubdomain(articleId,WebConst.SUB_COMMON_DOMAIN);
        String currentTime = Clock.systemDefaultZone().millis() + "";
        String encodeTime = Base64Util.encode(currentTime);
        return new ModelAndView("redirect:http://"+WechatUtil.getRandomChar()+"."+wySubdomain.getSubDomain()+"/1111/content/"+articleId+"?id="+encodeTime+"&secret="+currentTime);
    }
    /**
     * 新防风界面带封面2
     * @param request
     * @param model
     * @return
     */
    // @RequestMapping("/content/{articleId}")
    @RequestMapping("/zxplat/{articleId}")
    public ModelAndView shareConent(HttpServletRequest request, HttpServletResponse response,Model model,@PathVariable int articleId) {
        ModelAndView modelAndView = new ModelAndView();
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        if(articleWithImages.getWyArticle().getIsOrderImg()) {
            if (!HttpRequestDeviceUtils.isMobileDevice(request))
                return new ModelAndView("redirect:http://www.baidu.com");
        }
        //数据迁移
        if(!Objects.isNull(articleWithImages) && !StringUtils.isEmpty(articleWithImages.getWyArticle().getDataTransferUrl()))
            return new ModelAndView("redirect:"+articleWithImages.getWyArticle().getDataTransferUrl());
        //多链数据分流
        if(!Objects.isNull(articleWithImages.getWyArticle()) && !StringUtils.isEmpty(articleWithImages.getWyArticle().getNoShareDomain())) {
            String[] flurl = articleWithImages.getWyArticle().getNoShareDomain().split(",");
            //随机跳一个url
            int v = (int)Math.floor(Math.random() * flurl.length);
            return new ModelAndView("redirect:"+flurl[v]);

        }
        //取缓存
        String html = String.valueOf(redisObj.getRedisValueByKey("zxplat_content:"+articleId));
        if(StringUtils.isEmpty(html) || "null".equals(html)) {
            model.addAttribute("article", articleWithImages);
            //手动渲染
            SpringWebContext ctx = new SpringWebContext(request,response,
                    request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
            html = thymeleafViewResolver.getTemplateEngine().process("html/fmff/content", ctx);
            redisObj.saveDataToRedis("zxplat_content:"+articleId,html);
        }
        modelAndView.setViewName("html/fmff/content");
        model.addAttribute("article",articleWithImages);
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
    @RequestMapping("/jpff-wx/{articleId}")
    public ModelAndView jpff(HttpServletRequest request, Model model,@PathVariable int articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!HttpRequestDeviceUtils.isMobileDevice(request))
            return new ModelAndView("redirect:http://www.baidu.com");
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        if(!Objects.isNull(articleWithImages) && !StringUtils.isEmpty(articleWithImages.getWyArticle().getDataTransferUrl()))
            return new ModelAndView("redirect:"+articleWithImages.getWyArticle().getDataTransferUrl());
        //获取域名集合map
        WySubdomain wySubdomain = getWySubdomain(articleId,WebConst.SUB_COMMON_DOMAIN);
        String encodeTime = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        String contentDomain = WechatUtil.getRandomChar() + "." + wySubdomain.getSubDomain();
        // contentDomain = "localhost:8081";
        String contentUrl = "http://"+contentDomain+"/1111/5555/"+articleId+"?id="+encodeTime+"&cid=12306";
        model.addAttribute("content_url",Base64Util.encode(contentUrl));
        modelAndView.setViewName("html/cyff/jpff");
        return modelAndView;
    }
    /**
     * 新防封界面
     * @param request
     * @return
     */
    // @RequestMapping("/jpzsff/{articleId}")
    @RequestMapping("/yhwmjm/{articleId}")
    public ModelAndView jpzsff(HttpServletRequest request, HttpServletResponse response, Model model,@PathVariable int articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if(!HttpRequestDeviceUtils.isMobileDevice(request)) return new ModelAndView("redirect:http://www.baidu.com");
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        if(!Objects.isNull(articleWithImages) && !StringUtils.isEmpty(articleWithImages.getWyArticle().getDataTransferUrl()))
            return new ModelAndView("redirect:"+articleWithImages.getWyArticle().getDataTransferUrl());
        //数据迁移
        if(!Objects.isNull(articleWithImages) && !StringUtils.isEmpty(articleWithImages.getWyArticle().getDataTransferUrl()))
            return new ModelAndView("redirect:"+articleWithImages.getWyArticle().getDataTransferUrl());
        //多链数据分流
        if(!Objects.isNull(articleWithImages.getWyArticle()) && !StringUtils.isEmpty(articleWithImages.getWyArticle().getNoShareDomain())) {
            String[] flurl = articleWithImages.getWyArticle().getNoShareDomain().split(",");
            //随机跳一个url
            int v = (int)Math.floor(Math.random() * flurl.length);
            return new ModelAndView("redirect:"+flurl[v]);

        }
        /***********************************内容展示start*************************/
        //取缓存
        String html = String.valueOf(redisObj.getRedisValueByKey("images_list:"+articleId));
        if(StringUtils.isEmpty(html) || "null".equals(html)) {
            model.addAttribute("article", articleWithImages);
            //手动渲染
            SpringWebContext ctx = new SpringWebContext(request,response,
                    request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
            html = thymeleafViewResolver.getTemplateEngine().process("images_list", ctx);
            redisObj.saveDataToRedis("images_list:"+articleId,html);
        }
        articleWithImages.setContentHtml(html);
        model.addAttribute("article", articleWithImages);

        /***********************************内容展示end*************************/

        modelAndView.setViewName("html/cyff/jpzsff");
        return modelAndView;
    }
    /**
     * 新防风界面带封面2
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/jump-wx/{articleId}")
    public ModelAndView wmpt(HttpServletRequest request, Model model,@PathVariable int articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!HttpRequestDeviceUtils.isMobileDevice(request))
            return new ModelAndView("redirect:http://www.baidu.com");
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        if(!Objects.isNull(articleWithImages) && !StringUtils.isEmpty(articleWithImages.getWyArticle().getDataTransferUrl()))
            return new ModelAndView("redirect:"+articleWithImages.getWyArticle().getDataTransferUrl());
        //获取域名集合map
        WySubdomain wySubdomain = getWySubdomain(articleId,WebConst.SUB_COMMON_DOMAIN);
        String encodeTime = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        String contentDomain = WechatUtil.getRandomChar() + "." + wySubdomain.getSubDomain();
        // contentDomain = "localhost:8081";
        return new ModelAndView("redirect:http://"+contentDomain+"/1111/4444/"+articleId+"?id="+encodeTime+"&cid=12306");
    }
    /**
     * 新防封界面
     * @param request
     * @return
     */
    // @RequestMapping("/zsff/{articleId}")
    @RequestMapping("/4444/{articleId}")
    public ModelAndView realFangFeng(HttpServletRequest request, HttpServletResponse response, Model model,@PathVariable int articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if(!HttpRequestDeviceUtils.isMobileDevice(request)) return new ModelAndView("redirect:http://www.baidu.com");
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        if(!Objects.isNull(articleWithImages) && !StringUtils.isEmpty(articleWithImages.getWyArticle().getDataTransferUrl()))
            return new ModelAndView("redirect:"+articleWithImages.getWyArticle().getDataTransferUrl());
        //判断是否开启防封记录，如何开启，则打开记录防封
        if(articleWithImages.getWyArticle().getForcedShare() && redislogff(request)) {
            return new ModelAndView("redirect:http://"+WechatUtil.getRandomChar()+".hdisahfo.cn/54395/rew");
        }
        /***********************************内容展示start*************************/

        //获取图片相关信息
        ArticleWithImages article = iArticleService.queryCurrentArticle(articleId);
        //取缓存
        String html = String.valueOf(redisObj.getRedisValueByKey("images_list:"+articleId));
        if(StringUtils.isEmpty(html) || "null".equals(html)) {
            model.addAttribute("article", article);
            //手动渲染
            SpringWebContext ctx = new SpringWebContext(request,response,
                    request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
            html = thymeleafViewResolver.getTemplateEngine().process("images_list", ctx);
            redisObj.saveDataToRedis("images_list:"+articleId,html);
        }
        article.setContentHtml(html);
        String encodeTime = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        if(article.getWyArticle().getForcedShare()) {
            WySubdomain wySubdomain = getWySubdomain(articleId,WebConst.SUB_SHARE_DOMAIN);
            article.setDomainUrl(getDomainName(wySubdomain.getSubDomain()));
            String contentDomain = WechatUtil.getRandomChar() + "." + wySubdomain.getSubDomain();
            // contentDomain = "localhost:8081";
            article.setShareUrl("http://"+contentDomain+"/1111/jump-wx/"+articleId+"?"+encodeTime);
        }
        log.info("日志输出：{}",request.getRequestURI().toString());
        model.addAttribute("article", article);

        /***********************************内容展示end*************************/

        modelAndView.setViewName("html/cyff/zsff");
        return modelAndView;
    }
    /**
     * 新防封内容展示
     * @param request
     * @param cid     文章id
     * @return
     */
    // @RequestMapping("/content")
    @RequestMapping("/2222")
    @ResponseBody
    public ArticleWithImages realFangFengCon(HttpServletRequest request, HttpServletResponse response, Model model,int cid) {
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
        article.setShareUrl("http://"+WechatUtil.getRandomChar()+"."+wySubdomain.getSubDomain()+"/1111/jump-wx/"+cid+"?"+encodeTime);
        log.info("日志输出：{}",request.getRequestURI().toString());
        return article;
    }
    /***************************万铭公司平台加载图片分享end*******************************/


    /***************************mylove界面start*******************************/
    /**
     * 新防风界面带封面2
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/jump-love/{articleId}")
    public ModelAndView mylove(HttpServletRequest request, Model model,@PathVariable int articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!HttpRequestDeviceUtils.isMobileDevice(request))
            return new ModelAndView("redirect:http://www.baidu.com");
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        if(!Objects.isNull(articleWithImages) && !StringUtils.isEmpty(articleWithImages.getWyArticle().getDataTransferUrl()))
        return new ModelAndView("redirect:"+articleWithImages.getWyArticle().getDataTransferUrl());
        //获取域名集合map
        WySubdomain wySubdomain = getWySubdomain(articleId,WebConst.SUB_COMMON_DOMAIN);
        String encodeTime = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        String contentDomain = WechatUtil.getRandomChar() + "." + wySubdomain.getSubDomain();
        // contentDomain = "localhost:8081";
        return new ModelAndView("redirect:http://"+contentDomain+"/1111/newff/"+articleId+"?id="+encodeTime+"&cid=12306");
    }
    /**
     * 新防风界面带封面3
     * @param request
     * @param model
     * @return
     */
    // @RequestMapping("/newff/{articleId}")
    @RequestMapping("/120/{articleId}")
    public ModelAndView newFangFeng(HttpServletRequest request, Model model, HttpServletResponse response, @PathVariable int articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!HttpRequestDeviceUtils.isMobileDevice(request))
              return new ModelAndView("redirect:http://www.baidu.com");
        //获取图片相关信息
        ArticleWithImages article = iArticleService.queryCurrentArticle(articleId);
        //判断是否开启防封记录，如何开启，则打开记录防封
        if(article.getWyArticle().getForcedShare() && redislogff(request)) {
            return new ModelAndView("redirect:http://"+WechatUtil.getRandomChar()+".hdisahfo.cn/54395/rew");
        }
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
        String encodeTime = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        if(article.getWyArticle().getForcedShare()) {
            WySubdomain wySubdomain = getWySubdomain(articleId,WebConst.SUB_SHARE_DOMAIN);
            article.setDomainUrl(getDomainName(wySubdomain.getSubDomain()));
            String shareDomain = WechatUtil.getRandomChar() + "." + wySubdomain.getSubDomain();
            article.setShareUrl("http://"+shareDomain+"/1111/jump-love/"+articleId+"?"+encodeTime);
        }
        modelAndView.addObject("article",article);
        modelAndView.setViewName("html/wode/mylove");
        return modelAndView;
    }
    /**
     * 原3333
     * @param request
     * @param model
     * @return
     */
    // @RequestMapping("/newff/{articleId}")
    @RequestMapping(value="/3333/{articleId}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String sansan(HttpServletRequest request, Model model, HttpServletResponse response, @PathVariable int articleId) {
        ArticleWithImages article = iArticleService.queryCurrentArticle(articleId);
        if(article.getWyArticle().getIsOrderImg()) {
            if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        }
        //1.从redis缓存中查询
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("articlenew_list:"+articleId));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //获取图片相关信息
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
        model.addAttribute("article", article);
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("html/wode/mylove", ctx);
        redisObj.saveDataToRedis("articlenew_list:"+articleId,showhtml);
        log.info("日志输出：{}",request.getRequestURI().toString());
        return showhtml;
    }
    /***************************mylove界面end*******************************/
    /**
     * 获取内容，包子平台
     * @param request
     * @return
     */
    @RequestMapping(value="/limitme/{articleId}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String wxy(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable int articleId) {
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
        MobileUtil.analysisMobileFrom(request,redisObj,article.getWyArticle().getUserId());
        //1.从redis缓存中查询
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("limitme_list:"+articleId));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
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
        model.addAttribute("article",article);
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("html/h5/wxy", ctx);
        redisObj.saveDataToRedis("limitme_list:"+articleId,showhtml);
        return showhtml;

    }
    /**
     * 获取内容
     * @param request
     * @return
     */
    @RequestMapping("/getArticle/{articleId}")
    @ResponseBody
    public ArticleWithImages newlove(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable int articleId) {

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
        return article;

    }
    /**
     * 获取内容
     * @param request
     * @return
     */
    @RequestMapping(value="/show/{articleId}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String sansan1(HttpServletRequest request, Model model, HttpServletResponse response, @PathVariable int articleId) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        ArticleWithImages article = iArticleService.queryCurrentArticle(articleId);
        if(!Objects.isNull(article) && !StringUtils.isEmpty(article.getWyArticle().getDataTransferUrl())) {
            String qyhtml = transferUrl(request, model, response, article);
            return qyhtml;
        }
        //1.从redis缓存中查询
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("articlenew_list:"+articleId));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //获取图片相关信息
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
        model.addAttribute("article", article);
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("html/wode/mylove", ctx);
        redisObj.saveDataToRedis("articlenew_list:"+articleId,showhtml);
        log.info("日志输出：{}",request.getRequestURI().toString());
        return showhtml;
    }
    /**
     * 获取内容
     * @param request
     * @return
     */
    @RequestMapping("/u-9999/{articleId}")
    public ModelAndView othernew(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable int articleId) {

        //获取图片相关信息
        ArticleWithImages article = iArticleService.queryCurrentArticle(articleId);
        String noShareDomain = getString(article.getWyArticle().getNoShareDomain());
        return new ModelAndView("redirect:http://"+noShareDomain+"/1111/8888?id="+articleId);

    }
    private String getString(String noShareDomain) {
        if(!StringUtils.isEmpty(noShareDomain)) {
            String[] wySubs = noShareDomain.split(",");
            int v = (int)Math.floor(Math.random() * wySubs.length); //在域名中求整数
            noShareDomain = wySubs[v];
        }
        return noShareDomain;
    }

    @RequestMapping(value="/wmjm/{articleId}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toredis(HttpServletRequest request, HttpServletResponse response, Model model,@PathVariable int articleId) {

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
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("wmjm_content:"+articleId));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //取缓存
        String html = String.valueOf(redisObj.getRedisValueByKey("images_list:"+articleId));
        if(StringUtils.isEmpty(html) || "null".equals(html)) {
            model.addAttribute("article", article);
            //手动渲染
            SpringWebContext ctx = new SpringWebContext(request,response,
                    request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
            html = thymeleafViewResolver.getTemplateEngine().process("images_list", ctx);
            redisObj.saveDataToRedis("images_list:"+articleId,html);
        }
        article.setContentHtml(html);
        model.addAttribute("article", article);
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("html/cyff/zsff", ctx);
        redisObj.saveDataToRedis("wmjm_content:"+articleId,showhtml);
        /***********************************内容展示end*************************/

        // modelAndView.setViewName("html/cyff/zsff");
        return showhtml;
    }
    /**
     * 获取内容 article find形式
     * @param request
     * @return
     */
    @RequestMapping("/article/find/{articleId}")
    public ModelAndView  iframe(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable int articleId) {
        //获取图片相关信息
        ArticleWithImages article = iArticleService.queryCurrentArticle(articleId);
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return new ModelAndView("html/baozi/kb");
        //单片数据迁移
        if(!Objects.isNull(article) && !StringUtils.isEmpty(article.getWyArticle().getDataTransferUrl()))
            return new ModelAndView("redirect:"+article.getWyArticle().getDataTransferUrl());
        //多链数据分流
        if(!Objects.isNull(article) && !StringUtils.isEmpty(article.getWyArticle().getNoShareDomain())) {
            String[] flurl = article.getWyArticle().getNoShareDomain().split(",");
            //随机跳一个url
            int v = (int)Math.floor(Math.random() * flurl.length);
            return new ModelAndView("redirect:"+flurl[v]);

        }
        MobileUtil.analysisMobileFrom(request,redisObj,article.getWyArticle().getUserId());
        model.addAttribute("article",article);
        return new ModelAndView("html/baozi/iframe");

    }
    /**
     * 获取内容 article find形式
     * @param request
     * @return
     */
    @RequestMapping("/article/find-me/{articleId}")
    public ModelAndView  topiframe(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable int articleId) {
        //获取图片相关信息
        ArticleWithImages article = iArticleService.queryCurrentArticle(articleId);
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return new ModelAndView("html/baozi/kb");
        //单片数据迁移
        if(!Objects.isNull(article) && !StringUtils.isEmpty(article.getWyArticle().getDataTransferUrl()))
            return new ModelAndView("redirect:"+article.getWyArticle().getDataTransferUrl());
        //多链数据分流
        if(!Objects.isNull(article) && !StringUtils.isEmpty(article.getWyArticle().getNoShareDomain())) {
            String[] flurl = article.getWyArticle().getNoShareDomain().split(",");
            //随机跳一个url
            int v = (int)Math.floor(Math.random() * flurl.length);
            return new ModelAndView("redirect:"+flurl[v]);

        }
        MobileUtil.analysisMobileFrom(request,redisObj,article.getWyArticle().getUserId());
        model.addAttribute("article",article);
        return new ModelAndView("html/cjh5/cj4/iframe");

    }
    /**
     * 获取域名
     * @param articleId
     * @return
     */
    private WySubdomain getWySubdomain(@PathVariable int articleId,String domainType) {
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
    private String getDomainFlag(@PathVariable int articleId) {
        String articleIds = articleId+"";
        return ConstFinal.DOMAIN_MAP+"_"+articleIds;
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
    private String transferUrl(HttpServletRequest request, Model model, HttpServletResponse response, ArticleWithImages article) {
        model.addAttribute("article", article);
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        return thymeleafViewResolver.getTemplateEngine().process("html/wode/qy", ctx);
    }
}
