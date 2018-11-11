package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.config.WebConst;
import com.bray.dto.ConstFinal;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.Vo.ArticleNewImages;
import com.bray.model.WySubdomain;
import com.bray.service.IArticleService;
import com.bray.service.IDomainWebService;
import com.bray.util.Base64Util;
import com.bray.util.HttpRequestDeviceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring4.context.SpringWebContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 14:05 2018/11/2
 * @Modified By:
 */
@RequestMapping("/jzff")
@Controller
@Slf4j
public class WechatAritcleController {

    @Autowired
    private IArticleService iArticleService;

    @Resource
    private IDomainWebService iDomainWebService;

    /**
     * 新防封内容展示
     *
     * @param articleId 文章id
     * @return
     */
    @RequestMapping("/{articleId}")
    public ModelAndView realFangFengCon(HttpServletRequest request, Model model, @PathVariable String articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!HttpRequestDeviceUtils.isMobileDevice(request))
            return new ModelAndView("redirect:http://www.pinduoduo.com");
        ArticleNewImages articleNewImages = iArticleService.queryNewArticleImages(articleId);
        if(!Objects.isNull(articleNewImages) && !StringUtils.isEmpty(articleNewImages.getData().getDataTransferUrl()))
            return new ModelAndView("redirect:"+articleNewImages.getData().getDataTransferUrl());
        model.addAttribute("newarticle", Base64Util.encode(JSONObject.toJSONString(articleNewImages)));
        modelAndView.setViewName("html/newff2/jiazaiHtml");
        return modelAndView;
    }
    /**
     * 新防风界面带封面2
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/jump/{articleId}")
    public ModelAndView shareFangfang(HttpServletRequest request, Model model,@PathVariable String articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!HttpRequestDeviceUtils.isMobileDevice(request))
            return new ModelAndView("redirect:http://www.pinduoduo.com");
        //获取域名集合map
        Map<String,Object> domainMap = (HashMap<String,Object>)
                iDomainWebService.queryDomainByredisServer(getDomainFlag(articleId),articleId);
        List<WySubdomain> wySubdomains = (List<WySubdomain>) domainMap.get(WebConst.SUB_COMMON_DOMAIN);
        WySubdomain wySubdomain = new WySubdomain();
        if(!StringUtils.isEmpty(wySubdomains)) {
            wySubdomain = wySubdomains.get(0);
        } else {
            wySubdomain.setSubDomain("www.pinduoduo.com");
        }
        String encodeTime = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        return new ModelAndView("redirect:http://"+wySubdomain.getSubDomain()+"/jzff/content/"+articleId+"?"+encodeTime);
    }
    /**
     * 新防风界面带封面2
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/content/{articleId}")
    public ModelAndView shareConent(HttpServletRequest request, Model model,@PathVariable String articleId) {
        ModelAndView modelAndView = new ModelAndView();
        if (!HttpRequestDeviceUtils.isMobileDevice(request))
            return new ModelAndView("redirect:http://www.pinduoduo.com");
        //获取域名集合map
        Map<String,Object> domainMap = (HashMap<String,Object>)
                iDomainWebService.queryDomainByredisServer(getDomainFlag(articleId),articleId);
        List<WySubdomain> wySubdomains = (List<WySubdomain>) domainMap.get(WebConst.SUB_SHARE_DOMAIN);
        WySubdomain wySubdomain = new WySubdomain();
        if(!StringUtils.isEmpty(wySubdomains)) {
            wySubdomain = wySubdomains.get(0);
        } else {
            wySubdomain.setSubDomain("www.pinduoduo.com");
        }
        String encodeTime = Base64Util.encode(Clock.systemDefaultZone().millis() + "");
        ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
        modelAndView.setViewName("html/fmff/content");
        model.addAttribute("article",articleWithImages);
        model.addAttribute("domainUrl",wySubdomain.getSubDomain());
        model.addAttribute("shareUrl","http://"+wySubdomain.getSubDomain()+"/jzff/jump/"+articleId+"?"+encodeTime);
        return modelAndView;
    }

    /**
     * 获取域名map集合值
     * @param articleId
     * @return
     */
    private String getDomainFlag(@PathVariable String articleId) {
        return ConstFinal.DOMAIN_MAP+"_"+articleId;
    }
}
