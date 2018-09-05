package com.bray.web.controller;

import com.bray.dto.ConstFinal;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.ArticleModelVo;
import com.bray.service.IArticleAdminService;
import com.bray.util.ArticleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:wuzhiyuan
 * @Description: 文章相关控制器
 * @Date: Created in 11:06 2018/9/4
 * @Modified By:
 */
@Controller
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    @Resource
    private IArticleAdminService iArticleAdminService;

    @RequestMapping("/jump")
    public String jump() {

        return "/article/article-add";
    }
    /**
     * 文章编辑上传
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/img-upload")
    @ResponseBody
    public RestResponseBo imageupdate(HttpServletRequest httpServletRequest) {
        String articleId = httpServletRequest.getParameter(ConstFinal.ARTICLE_ID);
        String imgPath = ArticleUtil.uploadFileTransfer((MultipartHttpServletRequest) httpServletRequest, articleId);
        iArticleAdminService.insertArticleImg(articleId,imgPath);
        return RestResponseBo.ok();
    }
    /**
     * 文章添加
     * @param articleModelVo
     * @return
     */
    @PostMapping("/article-add")
    @ResponseBody
    public RestResponseBo articleAdd(ArticleModelVo articleModelVo) {
        if(ArticleUtil.verifyEmpty(articleModelVo)) {
            return RestResponseBo.fail("必填项必须填写！");
        }
        //获取新增的文章id，备添加图片使用
        String articleId = iArticleAdminService.insertArticle(articleModelVo);
        return new RestResponseBo(true,articleId);
    }
}
