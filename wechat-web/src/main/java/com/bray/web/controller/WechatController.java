package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.config.WebConst;
import com.bray.dto.ConstFinal;
import com.bray.dto.UrlConstant;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.WyOrder;
import com.bray.service.IArticleService;
import com.bray.service.IDomainWebService;
import com.bray.service.impl.WechatAuthServiceImpl;
import com.bray.util.Base64Util;
import com.bray.util.DateUtil;
import com.bray.util.WechatUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.Clock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 23:50 2018/8/24
 * @Modified By:
 */
@RequestMapping("/wechat")
@Controller
@Slf4j
public class WechatController {
    @Autowired
    private WechatAuthServiceImpl wechatAuthService;
    @Autowired
    private IArticleService iArticleService;
    @Resource
    private IDomainWebService iDomainWebService;
    /**
     * 首页跳转
     */
    @RequestMapping("/jump/{articleId}/{temstamp}")
    public String index(@PathVariable String articleId,@PathVariable String temstamp, Model model) {
        Map<String,Object> domainMap = (HashMap<String,Object>)iDomainWebService.queryDomainByredisServer(ConstFinal.DOMAIN_MAP);
        //获取内容跳转链接
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.SUB_COMMON_DOMAIN, UrlConstant.PATH_CONTENT_URL,articleId,domainMap);
        String contentUrl = String.valueOf(jsonObject.get("url"));
//        contentUrl = "http://www.kdxny67.cn/wechat/random-content-other/"+articleId+"/"+Clock.systemDefaultZone().millis();
        //base64编码
        model.addAttribute(UrlConstant.CONTENT_URL, Base64Util.encode(contentUrl));
        log.info("----------内容链接为：{}",contentUrl);
        return "html/share";
    }
    /**
     * 内容跳转
     */
    @RequestMapping("/content/{articleId}/{timstamp}")
    public String content(@PathVariable String articleId, String timstamp, Model model, HttpServletRequest request) {
        Map<String,Object> domainMap = (HashMap<String,Object>)iDomainWebService.queryDomainByredisServer(ConstFinal.DOMAIN_MAP);
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.SUB_SHARE_DOMAIN, UrlConstant.PATH_JUMP_RUL,articleId, domainMap);
        String shareUrl = String.valueOf(jsonObject.get("url"));
        //base64编码
        model.addAttribute(UrlConstant.SHARE_URL, Base64Util.encode(shareUrl));
        String requestURI = request.getRequestURI();
        //放置签名信息
        String signature = wechatAuthService.signature(requestURI);
        model.addAttribute("signature",signature);
        log.info("----------分享链接为：{}",shareUrl);
        return "html/content";
    }
    /**
     * 内容跳转
     */
    @RequestMapping("/random-content/{articleId}/{timstamp}")
    public ModelAndView randomContent(@PathVariable String articleId,@PathVariable String timstamp,Model model, HttpServletRequest request) {
        Map<String,Object> domainMap = (HashMap<String,Object>)iDomainWebService.queryDomainByredisServer(ConstFinal.DOMAIN_MAP);
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
     * 内容跳转
     */
    @RequestMapping("/random-content-other/{articleId}/{timstamp}")
    public String randomContentOther(@PathVariable String articleId,@PathVariable String timstamp,Model model, HttpServletRequest request) {
        //获取域名集合map
        Map<String,Object> domainMap = (HashMap<String,Object>)iDomainWebService.queryDomainByredisServer(ConstFinal.DOMAIN_MAP);
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.SUB_SHARE_DOMAIN, UrlConstant.PATH_JUMP_RUL,articleId,domainMap);
        String shareUrl = String.valueOf(jsonObject.get("url"));
        //获取图片相关信息
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        //base64编码
        model.addAttribute(UrlConstant.SHARE_URL, Base64Util.encode(shareUrl));
        model.addAttribute("article",articleWithImages);
//        model.addAttribute("signature",signature);
        log.info("----------分享链接为：{}",shareUrl);
        return "html/random-content-other";
    }

//    @RequestMapping("/jump/{articleId}/{timstamp}")
//    public String jumpOther(@PathVariable String articleId,@PathVariable String timstamp,Model model) {
//        //获取图片相关信息
//        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
//        //base64编码
//        model.addAttribute("article",articleWithImages);
//        return "html/random-common";
//    }
    /**
     * 无需强制分享普通界面
     */
    @RequestMapping("/random-common/{articleId}/{timstamp}")
    public String randomCommon(@PathVariable String articleId, @PathVariable String timstamp, Model model, HttpServletRequest request) {
        //获取域名集合map
        Map<String,Object> domainMap = (HashMap<String,Object>)iDomainWebService.queryDomainByredisServer(ConstFinal.DOMAIN_MAP);
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
}