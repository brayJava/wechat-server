package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.config.WebConst;
import com.bray.dto.ConstFinal;
import com.bray.dto.UrlConstant;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.WyArticle;
import com.bray.service.IArticleService;
import com.bray.service.IDomainWebService;
import com.bray.util.Base64Util;
import com.bray.util.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import static com.bray.dto.UrlConstant.PATH_CONTENT_URL;

/**
 * @Author:wuzhiyuan
 * @Description: 文章访问
 * @Date: Created in 23:50 2018/8/24
 * @Modified By:
 */
@RequestMapping("/wechat")
@Controller
@Slf4j
public class WechatController {
    @Autowired
    private IArticleService iArticleService;
    @Resource
    private IDomainWebService iDomainWebService;
    /**
     * 首页跳转
     */
    @RequestMapping("/jump/{articleId}/{temstamp}")
    public String index(@PathVariable String articleId,@PathVariable String temstamp, Model model) {
        Map<String,Object> domainMap = (HashMap<String,Object>)
                iDomainWebService.queryDomainByredisServer(getDomainFlag(articleId),articleId);
        //获取内容跳转链接
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.SUB_COMMON_DOMAIN
                , PATH_CONTENT_URL,articleId,domainMap);
        String contentUrl = String.valueOf(jsonObject.get("url"));
        //base64编码
        model.addAttribute(UrlConstant.CONTENT_URL, Base64Util.encode(contentUrl));
        log.info("----------内容链接为：{}",contentUrl);
        return "html/share";
    }
    /**
     * 首页跳转测试用
     */
    @RequestMapping("/jump-test/{articleId}/{temstamp}")
    public String indexTest(@PathVariable String articleId,@PathVariable String temstamp, Model model) {
        Map<String,Object> domainMap = (HashMap<String,Object>)
                iDomainWebService.queryDomainByredisServer(getDomainFlag(articleId),articleId);
        //获取内容跳转链接
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.SUB_COMMON_DOMAIN
                , UrlConstant.PATH_CONTENT_URL_TEST,articleId,domainMap);
        String contentUrl = String.valueOf(jsonObject.get("url"));
        //base64编码
        model.addAttribute(UrlConstant.CONTENT_URL, Base64Util.encode(contentUrl));
        log.info("----------内容链接为：{}",contentUrl);
        return "html/share";
    }
    /**
     * 内容跳转
     */
    @RequestMapping("/random-content-other/{articleId}/{timstamp}")
    public ModelAndView randomContentOther(@PathVariable String articleId,@PathVariable String timstamp,Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        /******防封缓存界面只能访问一次，再次访问如果存在则打开失败**********/
        String requestURI = request.getRequestURI();
        String contentUrlCache = requestURI.substring(requestURI.lastIndexOf("/") + 1, requestURI.length());
        String existHtmlUrl = iArticleService.queryExistHtmlUrl(contentUrlCache);
        if(!StringUtils.isEmpty(existHtmlUrl)) {
            modelAndView = new ModelAndView("redirect:http://www.baidu12321.com");
            return modelAndView;
        } else {
            iArticleService.insertHtmlUrlToRedis(contentUrlCache);
        }
        /******************防封缓存界面只能访问一次，end***************/

        Map<String,Object> domainMap = (HashMap<String,Object>)
                iDomainWebService.queryDomainByredisServer(getDomainFlag(articleId),articleId);
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.SUB_SHARE_DOMAIN, UrlConstant.PATH_JUMP_RUL,articleId,domainMap);
        //获取图片相关信息
        ArticleWithImages articleWithImages = getArticleWithImages(articleId);
        //获取图片相关信息
        // ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        String dataTransferUrl = articleWithImages.getWyArticle().getDataTransferUrl();
        //判断是否进行数据迁移
        if(!StringUtils.isEmpty(dataTransferUrl)) {
            modelAndView = new ModelAndView("redirect:" + buildRandomTransferUrl(dataTransferUrl));
            return modelAndView;
        }
        //base64编码
        modelAndView.addObject(UrlConstant.SHARE_URL, Base64Util.encode(String.valueOf(jsonObject.get("url"))));
        String domain = getDomainName(jsonObject);
        modelAndView.addObject(UrlConstant.DOMAIN_NAME, Base64Util.encode(domain));
        modelAndView.addObject("article",articleWithImages);
        modelAndView.addObject("currentTime",Clock.systemDefaultZone().millis()+"");
        modelAndView.setViewName("html/random-content-other");
        log.info("----------分享链接为：{}",String.valueOf(jsonObject.get("url")));
        return modelAndView;
    }
    /**
     * 内容跳转测试用
     */
    @RequestMapping("/random-test/{articleId}/{timstamp}")
    public ModelAndView randomTest(@PathVariable String articleId,@PathVariable String timstamp,Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String,Object> domainMap = (HashMap<String,Object>)
                iDomainWebService.queryDomainByredisServer(getDomainFlag(articleId),articleId);
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.SUB_SHARE_DOMAIN, UrlConstant.PATH_JUMP_RUL,articleId,domainMap);
        //获取图片相关信息
        ArticleWithImages articleWithImages = getArticleWithImages(articleId);
        String dataTransferUrl = articleWithImages.getWyArticle().getDataTransferUrl();
        //判断是否进行数据迁移
        if(!StringUtils.isEmpty(dataTransferUrl)) {
            modelAndView = new ModelAndView("redirect:" + buildRandomTransferUrl(dataTransferUrl));
            return modelAndView;
        }
        //base64编码
        modelAndView.addObject(UrlConstant.SHARE_URL, Base64Util.encode(String.valueOf(jsonObject.get("url"))));
        String domain = getDomainName(jsonObject);
        modelAndView.addObject(UrlConstant.DOMAIN_NAME, Base64Util.encode(domain));
        modelAndView.addObject("article",articleWithImages);
        modelAndView.setViewName("html/random-test");
        log.info("----------分享链接为：{}",String.valueOf(jsonObject.get("url")));
        return modelAndView;
    }
    /**
     * 内容跳转
     */
    @RequestMapping("/random-content/{articleId}/{timstamp}")
    public ModelAndView randomContent(@PathVariable String articleId,@PathVariable String timstamp,Model model, HttpServletRequest request) {
        Map<String,Object> domainMap = (HashMap<String,Object>)
                iDomainWebService.queryDomainByredisServer(getDomainFlag(articleId),articleId);
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.SUB_SHARE_DOMAIN, UrlConstant.PATH_JUMP_RUL,articleId, domainMap);
        String shareUrl = String.valueOf(jsonObject.get("url"));
        String domain = String.valueOf(jsonObject.get("domain"));
        ArticleWithImages articleWithImages = getArticleWithImages(articleId, domain);
        //base64编码
        model.addAttribute(UrlConstant.SHARE_URL, Base64Util.encode(shareUrl));
        model.addAttribute("article",articleWithImages);
//        model.addAttribute("signature",signature);
        log.info("----------分享链接为：{}",shareUrl);
        ModelAndView modelAndView =  new ModelAndView("redirect:http://localhost:8081/html/random-common.html");
        return modelAndView;
    }
    /**
     * 无需强制分享普通界面
     */
    @RequestMapping("/random-common/{articleId}/{timstamp}")
    public String randomCommon(@PathVariable String articleId, @PathVariable String timstamp, Model model, HttpServletRequest request) {
        //获取域名集合map
        Map<String,Object> domainMap = (HashMap<String,Object>)
                iDomainWebService.queryDomainByredisServer(getDomainFlag(articleId),articleId);
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.RONDOM_COMMON_DOMAIN, UrlConstant.PATH_WITHOUT_SHARE_RUL,articleId,domainMap);
        String withoutShareUrl = String.valueOf(jsonObject.get("url"));
//      //获取图片相关信息
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        //base64编码
        model.addAttribute("article",articleWithImages);
        model.addAttribute(UrlConstant.WITH_OUT_SHARE, Base64Util.encode(withoutShareUrl));
        log.info("----------分享链接为：{}",withoutShareUrl);
        return "html/random-common";
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
     * 获取文章信息
     * @param articleId
     * @param domain
     * @return
     */
    private ArticleWithImages getArticleWithImages(@PathVariable String articleId, String domain) {
        //获取图片相关信息
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        if(!Objects.isNull(articleWithImages) && articleWithImages.getWyArticle() != null) {
            articleWithImages.getWyArticleImgs().stream().peek(wyArticleImg ->
                    wyArticleImg.setImgPath(UrlConstant.HTTP_RUL+domain+":"+UrlConstant.PORT
                            + File.separator+wyArticleImg.getImgPath())).collect(Collectors.toList());
        }
        return articleWithImages;
    }
    /**
     * 获取域名名称（如为子域名，则截取主域名）
     * @param jsonObject
     * @return
     */
    private String getDomainName(JSONObject jsonObject) {
        String domain = String.valueOf(jsonObject.get("domain"));
        if(!StringUtils.isEmpty(domain)) {
            domain = domain.substring(domain.indexOf(".")+1, domain.length());
        }
        return domain;
    }
    /**
     * 获取随机数据迁移连接（如果不是本项目连接，则之间返回迁移链接）
     * @param dataTransferUrl
     * @return
     */
    private String buildRandomTransferUrl(String dataTransferUrl) {
        if(!dataTransferUrl.contains(PATH_CONTENT_URL)) {
            return dataTransferUrl;
        }
        return UrlConstant.HTTP_RUL
                + WechatUtil.getRandomNum()
                + dataTransferUrl
                + Clock.systemDefaultZone().millis();
    }
    /**
     *  获取所有文章集合
     * @param articleId
     * @return
     */
    private ArticleWithImages getArticleWithImages(@PathVariable String articleId) {
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        WyArticle wyArticle = articleWithImages.getWyArticle();
        wyArticle.setNoShareDomain(iArticleService.getNoShareDomainByArticleId(articleId));
        articleWithImages.setWyArticle(wyArticle);
        return articleWithImages;
    }
}