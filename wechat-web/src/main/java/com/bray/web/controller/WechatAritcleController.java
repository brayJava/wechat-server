package com.bray.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.Vo.ArticleNewImages;
import com.bray.service.IArticleService;
import com.bray.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 新防封内容展示
     * @param articleId     文章id
     * @return
     */
    @RequestMapping("/{articleId}")
    public String realFangFengCon( Model model, @PathVariable String articleId) {
        ArticleNewImages articleNewImages = iArticleService.queryNewArticleImages(articleId);
        model.addAttribute("newarticle", Base64Util.encode(JSONObject.toJSONString(articleNewImages)));
        return "html/newff2/jiazaiHtml";
    }
}
