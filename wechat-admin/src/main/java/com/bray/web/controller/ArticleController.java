package com.bray.web.controller;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.dto.ConstFinal;
import com.bray.dto.ConstatFinal;
import com.bray.model.Bo.ArticleImgModelVo;
import com.bray.model.Bo.ArticleWithImages;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.ArticleModelVo;
import com.bray.model.Vo.ArticleOtherModelVo;
import com.bray.model.Vo.SearchModelVo;
import com.bray.model.WyArticle;
import com.bray.model.WyUser;
import com.bray.service.IArticleAdminService;
import com.bray.service.IArticleService;
import com.bray.util.ArticleUtil;
import com.bray.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    @Resource
    private RedisPoolCache redisObj;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    private ApplicationContext applicationContext;

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
   public String articleImgEdit(int articleId,Model model) {
       ArticleWithImages articleWithImages = iArticleService.queryCurrentArticleBySql(articleId);
       model.addAttribute("articleImgs",articleWithImages.getArticleSubImages());
       model.addAttribute("article",articleWithImages.getWyArticle());
       return "article/article-img-edit";
   }
    /**
     * 文章列表展示
     * @return
     */
    @RequestMapping(value="/article-list",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String articleList(Model model,HttpServletRequest request, HttpServletResponse response) {
        //1.从redis缓存中查询
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("all_articles"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        HttpSession session = request.getSession();
        WyUser wyUser = (WyUser)session.getAttribute(ConstFinal.USER_NAME);
        List<WyArticle> wyArticles = iArticleService.queryAllEffectiveArticle(wyUser.getId());
        wyArticles.stream().forEach(wyArticle -> {
            Object redisValueByKey = redisObj.getRedisValueByKey(ConstatFinal.ARTICLE_H5_2_VAL + ":" + wyArticle.getId());
            wyArticle.setCjContentTemp(String.valueOf(redisValueByKey));
        });

        model.addAttribute("wyArticles",wyArticles);
        //起始时间为00:00:00
        String startDate  = LocalDateTime.of(LocalDate.now(), LocalTime.MIN)
                .format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_yyyy_MM_dd_HH_mm_ss));
        //结束时间为晚上23:59:59
        String endDate = LocalDateTime.of(LocalDate.now(), LocalTime.MAX)
                .format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_yyyy_MM_dd_HH_mm_ss));
        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("article/article-list", ctx);
        redisObj.saveDataToRedis("all_articles",showhtml);
        return showhtml;
    }
    /**
     * 文章修改
     * @return
     */
    @RequestMapping("/article-edit")
    public RestResponseBo articleEdit(ArticleOtherModelVo articleOtherModelVo,HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        WyUser wyUser = (WyUser)session.getAttribute(ConstFinal.USER_NAME);
        articleOtherModelVo.setUserId(wyUser.getId());
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
        // iArticleAdminService.insertArticleImg(articleId,imgPath);
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
    public RestResponseBo articleAddOther(ArticleOtherModelVo articleOtherModelVo,HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        WyUser wyUser = (WyUser)session.getAttribute(ConstFinal.USER_NAME);
        articleOtherModelVo.setUserId(wyUser.getId());
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
    public RestResponseBo articleRefresh(int articleId){
        iArticleAdminService.articleRefresh(articleId,"");
        return RestResponseBo.ok();
    }
    /**
     * 复制文章
     * @return
     */
    @RequestMapping("/article-copy")
    @ResponseBody
    public RestResponseBo articleCopy(int articleId){
        iArticleAdminService.articleCopy(articleId);
        return RestResponseBo.ok();
    }
    /**
     * 删除文章
     * @return
     */
    @RequestMapping("/article-del")
    @ResponseBody
    public RestResponseBo articleDel(int articleId){
        iArticleAdminService.articleDel(articleId);
        return RestResponseBo.ok();
    }
    /**
     * 文章图片删除
     */
    @RequestMapping("/img-del")
    @ResponseBody
    public RestResponseBo articleImgDel(int articleImgId){
        iArticleAdminService.delArticleImg(articleImgId);
        return RestResponseBo.ok();
    }
    /**
     * 文章图片修改
     */
    @RequestMapping("/img-update")
    @ResponseBody
    public RestResponseBo articleImgUpdate(int articleImgId,String imgUrl,String imgUrlHref,String subimgs){
        iArticleAdminService.updateArticleImg(articleImgId,imgUrl,imgUrlHref,subimgs);
        return RestResponseBo.ok();
    }
    /**
     * 文章图片插入界面
     */
    @RequestMapping("/article-img-insert")
    public String articleImgInsert(){
        return "article/article-img-insert";
    }
    /**
     * 文章图片插入
     */
    @RequestMapping("/image-add")
    @ResponseBody
    public RestResponseBo imageAdd(ArticleImgModelVo articleImgModelVo){
        iArticleAdminService.insertArticleImg(articleImgModelVo);
        return RestResponseBo.ok();
    }
}
