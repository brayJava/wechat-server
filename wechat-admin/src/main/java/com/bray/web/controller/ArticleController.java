package com.bray.web.controller;

import com.bray.dto.ConstFinal;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.ArticleModelVo;
import com.bray.model.Vo.ArticleOtherModelVo;
import com.bray.model.WyArticle;
import com.bray.service.IArticleAdminService;
import com.bray.service.IArticleService;
import com.bray.util.ArticleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    @Resource
    private IArticleService iArticleService;

    @RequestMapping("/jump")
    public String jump() {

        return "article/article-add";
    }

    /**
     * 跳随机界面
     * @return
     */
    @RequestMapping("/jump-other")
    public String jumpOther() {

        return "article/article-add-other";
    }
    /**
     * 跳编辑界面
     * @return
     */
    @RequestMapping("/jump-article-edit")
    public String jumpArticleEdit() {

        return "article/article-edit";
    }
   /**
    * 修改文章图片
    */
   @RequestMapping("/article-img-edit")
   public String articleImgEdit(String articleId,Model model) {
       ArticleWithImages articleWithImages = iArticleService.queryCurrentArticle(articleId);
       model.addAttribute("articleImgs",articleWithImages.getWyArticleImgs());
       return "article/article-img-edit";
   }
    /**
     * 文章列表展示
     * @return
     */
    @RequestMapping("/article-list")
    public String articleList(Model model) {
        List<WyArticle> wyArticles = iArticleService.queryAllEffectiveArticle();
        model.addAttribute("wyArticles",wyArticles);
        return "article/article-list";
    }
    /**
     * 文章修改
     * @return
     */
    @RequestMapping("/article-edit")
    public RestResponseBo articleEdit(ArticleOtherModelVo articleOtherModelVo) {
        iArticleAdminService.updateArticle(articleOtherModelVo);
        return RestResponseBo.ok();
    }
    /**
     * 文章编辑上传
     * @param httpServletRequest、
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
    /**
     * 第二种文章添加（第三方添加图片）
     * @return
     */
    @PostMapping("/article-add-other")
    @ResponseBody
    public RestResponseBo articleAddOther(ArticleOtherModelVo articleOtherModelVo) {
        //添加文章和图片连接
        iArticleAdminService.insertOtherArticleImg(articleOtherModelVo);
        return new RestResponseBo(true);
    }
    /**
     * 刷新文章
     * @return
     */
    @RequestMapping("/refresh-prod")
    @ResponseBody
    public RestResponseBo articleRefresh(String articleId){
        iArticleAdminService.articleRefresh(articleId);
        return RestResponseBo.ok();
    }
}
