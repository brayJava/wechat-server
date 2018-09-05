package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.config.WebConst;
import com.bray.dto.ConstFinal;
import com.bray.dto.UrlConstant;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.WyDomain;
import com.bray.model.WySubdomain;
import com.bray.service.IArticleService;
import com.bray.service.impl.WechatAuthServiceImpl;
import com.bray.util.Base64Util;
import com.bray.util.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.time.Clock;
import java.util.List;
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
    /**
     * 首页跳转
     */
    @RequestMapping("/jump/{temstamp}")
    String index(@PathVariable String temstamp, Model model) {
        //获取内容跳转链接
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.SUB_COMMON_DOMAIN, UrlConstant.PATH_CONTENT_URL);
        String contentUrl = String.valueOf(jsonObject.get("url"));
        //base64编码
        model.addAttribute(UrlConstant.CONTENT_URL, Base64Util.encode(contentUrl));
        log.info("----------内容链接为：{}",contentUrl);
        return "html/share";
    }
    /**
     * 内容跳转
     */
    @RequestMapping("/content/{timstamp}")
    String content(@PathVariable String timstamp,Model model) {
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.SUB_SHARE_DOMAIN, UrlConstant.PATH_JUMP_RUL);
        String shareUrl = String.valueOf(jsonObject.get("url"));
        //base64编码
        model.addAttribute(UrlConstant.SHARE_URL, Base64Util.encode(shareUrl));
        //放置签名信息
        String signature = wechatAuthService.signature("http://www.baidu.com");
        model.addAttribute("signature",signature);
        log.info("----------分享链接为：{}",shareUrl);
        return "html/content";
    }
    /**
     * 内容跳转
     */
    @RequestMapping("/random-content/{article}")
    String randomContent(@PathVariable String article,Model model) {
        JSONObject jsonObject = WechatUtil.nextUrlBuild(WebConst.SUB_SHARE_DOMAIN, UrlConstant.PATH_JUMP_RUL);
        String shareUrl = String.valueOf(jsonObject.get("url"));
        String domain = String.valueOf(jsonObject.get("domain"));
        //放置签名信息
        String signature = wechatAuthService.signature("http://www.baidu.com");
        //获取图片相关信息
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(article);
        articleWithImages.getWyArticleImgs().stream().peek(wyArticleImg ->
                wyArticleImg.setImgPath(UrlConstant.HTTP_RUL+domain+":"+UrlConstant.PORT+ File.separator+wyArticleImg.getImgPath())).collect(Collectors.toList());
        //base64编码
        model.addAttribute(UrlConstant.SHARE_URL, Base64Util.encode(shareUrl));
        model.addAttribute("article",articleWithImages);
        model.addAttribute("signature",signature);
        log.info("----------分享链接为：{}",shareUrl);
        return "html/random-content";
    }
    /**
     * 内容跳转
     */
    @RequestMapping("/story/{id}/{code}")
    String story(@PathVariable String id,@PathVariable String code) {
        return "order/story";
    }
    /**
     * 订单跳转
     */
    @RequestMapping("/order")
    String order() {
        return "order/order";
    }
}