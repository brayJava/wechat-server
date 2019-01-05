package com.bray.service.impl;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.dto.ConstFinal;
import com.bray.dto.ConstatFinal;
import com.bray.dto.EffectiveType;
import com.bray.dto.InsertType;
import com.bray.mapper.WyArticleImgMapper;
import com.bray.mapper.WyArticleMapper;
import com.bray.model.Bo.ArticleImgModelVo;
import com.bray.model.Vo.ArticleModelVo;
import com.bray.model.Vo.ArticleOtherModelVo;
import com.bray.model.WyArticle;
import com.bray.model.WyArticleImg;
import com.bray.model.WyArticleImgExample;
import com.bray.service.IArticleAdminService;
import com.bray.util.GUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author:wuzhiyuan
 * @Description: 文章管理接口
 * @Date: Created in 11:10 2018/9/5
 * @Modified By:
 */
@Service
@Slf4j
@Transactional
public class ArticleAdminServiceImpl implements IArticleAdminService {
    @Resource
    private WyArticleMapper wyArticleMapper;
    @Resource
    private WyArticleImgMapper wyArticleImgMapper;
    @Resource
    private RedisPoolCache redisCache;
    /**
     * 新增文章
     * @param articleModelVo
     */
    @Override
    public String insertArticle(Object articleModelVo) {
        String articleId = GUIDUtil.buildMd5GUID();
        ArticleModelVo article = (ArticleModelVo) articleModelVo;
        WyArticle wyArticle = new WyArticle();
        // wyArticle.setId(articleId);
        wyArticle.setTitle(article.getTitle());
        wyArticle.setBgmUrl(article.getBgmUrl());
        wyArticle.setAuthor(article.getAuthor());
        wyArticle.setShareTitle(article.getShareTitle());
        wyArticle.setShareDescribe(article.getShareDecribe());
        wyArticle.setIsOrderImg((ConstFinal
                .ARTICLE_STATUS.equals(article.getWhetherOrderImg()) ? true : false));
        wyArticle.setIsPublish((ConstFinal
                .ARTICLE_STATUS.equals(article.getWhetherPublish()) ? true : false));
        wyArticle.setStatus(EffectiveType.EFFECTIVE_YES);
        wyArticle.setCreateTime(new Date());
        wyArticle.setUpdateTime(new Date());
        try {
            wyArticleMapper.insertSelective(wyArticle);
        } catch (RuntimeException e) {
            log.error("------------文章添加失败---------");
            e.printStackTrace();
        }
        return articleId;
    }
    /**
     * 插入文章图片
     * @param articleId
     * @param imgPath
     */
    @Override
    public void insertArticleImg(int articleId, String imgPath) {
        WyArticleImg wyArticleImg = new WyArticleImg();
        wyArticleImg.setArticleId(articleId);
        wyArticleImg.setImgPath(imgPath);
        wyArticleImg.setStatus(EffectiveType.EFFECTIVE_YES);
        wyArticleImg.setCreateTime(new Date());
        wyArticleImg.setUpdateTime(new Date());
        try {
            wyArticleImgMapper.insertSelective(wyArticleImg);
        } catch (RuntimeException e) {
            log.error("-------插入文章图片失败-------");
            e.printStackTrace();
        }
    }
    /**
     * 第二种添加文章方式
     */
    @Override
    public void insertOtherArticleImg(Object obj) {
        // String articleId = GUIDUtil.buildMd5GUID();
        ArticleOtherModelVo articleOtherModelVo = (ArticleOtherModelVo) obj;
        WyArticle wyArticle = new WyArticle();
        // wyArticle.setId(articleId);
        wyArticle.setTitle(articleOtherModelVo.getTitle());
        wyArticle.setBgmUrl(articleOtherModelVo.getBgmUrl());
        wyArticle.setAuthor(articleOtherModelVo.getAuthor());
        wyArticle.setShareTitle(articleOtherModelVo.getShareTitle());
        wyArticle.setShareDescribe(articleOtherModelVo.getShareDescribe());
        wyArticle.setShareImgUrl(articleOtherModelVo.getShareImgUrl());
        wyArticle.setGobackUrl(articleOtherModelVo.getGobackUrl());
        wyArticle.setImageGobackUrl(articleOtherModelVo.getImageGobackUrl());
        wyArticle.setReadOriginalUrl(articleOtherModelVo.getReadOriginalUrl());
        wyArticle.setOrderImgUrl(articleOtherModelVo.getOrderImgUrl());
        wyArticle.setOrderCopyUrl(articleOtherModelVo.getOrderCopyUrl());
        wyArticle.setShareSuccessFailUrl(articleOtherModelVo.getShareSuccessFailUrl());
        wyArticle.setStatistical(articleOtherModelVo.getStatistical());
        wyArticle.setIsOrderImg(ConstFinal.ARTICLE_STATUS
                .equals(articleOtherModelVo.getOrderImg()) ? true : false);
        wyArticle.setIsPublish(ConstFinal.ARTICLE_STATUS
                .equals(articleOtherModelVo.getOrderImg()) ? true : false);
        wyArticle.setForcedShare(ConstFinal.SHARE_STATUS
                .equals(articleOtherModelVo.getForcedShare()) ? true : false);
        //按字符逗号隔开格式存入
        wyArticle.setNoShareDomain(dealNoShareDomain(articleOtherModelVo.getNoShareDomain()));
        wyArticle.setStatus(EffectiveType.EFFECTIVE_YES);
        wyArticle.setCreateTime(new Date());
        wyArticle.setUpdateTime(new Date());
        try {
            wyArticleMapper.insertSelective(wyArticle);
        } catch (RuntimeException e) {
            log.error("-----------文章添加失败----------");
            e.printStackTrace();
        }
        // String[] outerImgUrls = articleOtherModelVo.getOuterImgUrl();
        //插入新的文章图片
        WyArticle wy = wyArticleMapper.selectLastArticle();
        int arid = 1;
        if(!StringUtils.isEmpty(wy.getId())) {
            arid = wy.getId();
        }
        insertNewArticleImages(arid, articleOtherModelVo);
    }
    /**
     * 文章修改
     * @param obj
     */
    @Override
    public void updateArticle(Object obj) {

        ArticleOtherModelVo articleOtherModelVo = (ArticleOtherModelVo) obj;
        WyArticle wyArticle = new WyArticle();
        wyArticle.setId(articleOtherModelVo.getArticleId());
        wyArticle.setTitle(articleOtherModelVo.getTitle());
        wyArticle.setBgmUrl(articleOtherModelVo.getBgmUrl());
        wyArticle.setAuthor(articleOtherModelVo.getAuthor());
        wyArticle.setShareTitle(articleOtherModelVo.getShareTitle());
        wyArticle.setShareDescribe(articleOtherModelVo.getShareDescribe());
        wyArticle.setShareImgUrl(articleOtherModelVo.getShareImgUrl());
        wyArticle.setDataTransferUrl(articleOtherModelVo.getDataTransferUrl());
        wyArticle.setGobackUrl(articleOtherModelVo.getGobackUrl());
        wyArticle.setImageGobackUrl(articleOtherModelVo.getImageGobackUrl());
        wyArticle.setReadOriginalUrl(articleOtherModelVo.getReadOriginalUrl());
        wyArticle.setShareSuccessFailUrl(articleOtherModelVo.getShareSuccessFailUrl());
        wyArticle.setOrderImgUrl(articleOtherModelVo.getOrderImgUrl());
        wyArticle.setOrderCopyUrl(articleOtherModelVo.getOrderCopyUrl());
        wyArticle.setStatistical(articleOtherModelVo.getStatistical());
        wyArticle.setIsOrderImg(ConstFinal.ARTICLE_STATUS.equals(articleOtherModelVo.getOrderImg()) ? true : false);
        wyArticle.setForcedShare(ConstFinal.SHARE_STATUS.equals(articleOtherModelVo.getForcedShare()) ? true : false);
        wyArticle.setNoShareDomain(dealNoShareDomain(articleOtherModelVo.getNoShareDomain()));
        wyArticle.setUpdateTime(new Date());
        this.articleRefresh(articleOtherModelVo.getArticleId());
        try {
            wyArticleMapper.updateByPrimaryKeySelective(wyArticle);
        } catch (RuntimeException e) {
            log.error("--------------文章更新失败------------");
            e.printStackTrace();
        }
        //修改文章图片内容
        if(!StringUtils.isEmpty(articleOtherModelVo.getImagesUrl().trim())) {
            try {
                //作废历史图片
                int i = wyArticleImgMapper.updateByArticleId(EffectiveType.EFFECTIVE_NO, articleOtherModelVo.getArticleId());
                if(i == 0) return;
            } catch (Exception e) {
                log.error("--------------作废历史图片信息失败------------");
                e.printStackTrace();
            }
            //插入新的文章图片
            insertNewArticleImages(articleOtherModelVo.getArticleId(), articleOtherModelVo);

        }
    }
    /**
     * 刷新文章
     */
    public void articleRefresh(String articleId) {
        try {
            redisCache.deleteDataOfRedis(ConstatFinal.AUTHOR+":"+articleId);
            //删除图片相关内容
            redisCache.deleteDataOfRedis(ConstatFinal.IMAGES_LIST+":"+articleId);
            //删除新防封文案相关内容
            redisCache.deleteDataOfRedis(ConstatFinal.NEW_ARTICLE_LIST+":"+articleId);

            redisCache.deleteDataOfRedis(ConstatFinal.IMAGES_CONTENT+":"+articleId);
        } catch (Exception e) {
            log.error("-------文章redis更新");
            e.printStackTrace();
        }
    }
    /**
     * 插入新的文章图片
     * @param articleId
     * @param articleOtherModelVo
     */
    private void insertNewArticleImages(int articleId, ArticleOtherModelVo articleOtherModelVo) {
        String imagesUrlStr = articleOtherModelVo.getImagesUrl().trim();
        String replaceUrlStr = imagesUrlStr.replace(" ", "");
        String[] imagesUrls = replaceUrlStr.split("\n");
        if(Objects.isNull(imagesUrls) || imagesUrls.length == 0 || imagesUrls.length < 2) return;
        Arrays.stream(imagesUrls).forEach(outerImgUrl -> {
            if(StringUtils.isEmpty(outerImgUrl)) return;
            WyArticleImg wyArticleImg = new WyArticleImg();
            wyArticleImg.setThirdImgPath(outerImgUrl);
            wyArticleImg.setStatus(EffectiveType.EFFECTIVE_YES);
            wyArticleImg.setArticleId(articleId);
            wyArticleImg.setUpdateTime(new Date());
            wyArticleImg.setCreateTime(new Date());
            try {
                wyArticleImgMapper.insertSelective(wyArticleImg);
            } catch (RuntimeException e) {
                log.error("-----------关联图片添加失败----------");
                e.printStackTrace();
            }
        });
    }
    /**
     * 处理非分享域名，将其多个用逗号隔开存入库中
     * @param noShareDomain 非分享域名
     * @return
     */
    private String dealNoShareDomain(String noShareDomain) {
        if(StringUtils.isEmpty(noShareDomain)) return "";
        String replaceUrlStr = noShareDomain.trim().replace(" ", "");
        String[] noShareDomains = replaceUrlStr.split("\n");
        if(Objects.isNull(noShareDomains) || noShareDomains.length == 0) return "";
        String noShareDomainStr = Arrays.stream(noShareDomains).map(s -> s.trim()).collect(Collectors.joining(","));
        return noShareDomainStr;
    }
    /**
     * 删除文章图片
     * @param articleImgId
     */
    @Override
    public void delArticleImg(int articleImgId) {
        WyArticleImg wyArticleImg = new WyArticleImg();
        wyArticleImg.setId(articleImgId);
        wyArticleImg.setStatus(EffectiveType.EFFECTIVE_NO);
        try {
            wyArticleImgMapper.updateByPrimaryKeySelective(wyArticleImg);
        } catch (RuntimeException e) {
            log.error("---------删除文章图片失败！！！");
            e.printStackTrace();
        }
    }
    /**
     * 修改文章图片
     * @param articleImgId
     * @param imgUrlHref
     */
    @Override
    public void updateArticleImg(int articleImgId, String imgUrl, String imgUrlHref) {
        WyArticleImg wyArticleImg = new WyArticleImg();
        wyArticleImg.setId(articleImgId);
        wyArticleImg.setThirdImgPath(imgUrl);
        wyArticleImg.setImgUrl(imgUrlHref);
        try {
            wyArticleImgMapper.updateByPrimaryKeySelective(wyArticleImg);
        } catch (RuntimeException e) {
            log.error("---------修改文章图片失败！！！");
            e.printStackTrace();
        }
    }

    /**
     * 文章图片插入(向上与向下插入逻辑不一样)
     * @param articleImgModelVo
     */
    @Override
    public void insertArticleImg(ArticleImgModelVo articleImgModelVo) {

        if(!StringUtils.isEmpty(articleImgModelVo.getArticleId())
                && !StringUtils.isEmpty(articleImgModelVo.getArticleImgId())
                && !StringUtils.isEmpty(articleImgModelVo.getInsertType())
                && !StringUtils.isEmpty(articleImgModelVo.getImgUrl()))  {

            WyArticleImgExample wyArticleImgExample = new WyArticleImgExample();
            wyArticleImgExample.createCriteria()
                    .andArticleIdEqualTo(articleImgModelVo.getArticleId())
                    .andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
            List<WyArticleImg> wyArticleImgs = wyArticleImgMapper.selectByExample(wyArticleImgExample);
            //获取所有文章图片id，获取位置
            List<Integer> articleImgId = wyArticleImgs.stream().map(wyArticleImg -> wyArticleImg.getId()).collect(Collectors.toList());
            int articleImgIndex = articleImgId.indexOf(articleImgModelVo.getArticleImgId());
            WyArticleImg wyArticleImg = new WyArticleImg();
            wyArticleImg.setThirdImgPath(articleImgModelVo.getImgUrl());
            wyArticleImg.setStatus(EffectiveType.EFFECTIVE_YES);
            wyArticleImg.setArticleId(articleImgModelVo.getArticleId());
            wyArticleImg.setCreateTime(new Date());
            wyArticleImg.setUpdateTime(new Date());
            //如果向上插入信息则获取当前索引
            if(InsertType.upinsert .name().equals(articleImgModelVo.getInsertType())) {
                wyArticleImgs.add(articleImgIndex,wyArticleImg);
            } else if(InsertType.downinsert.name().equals(articleImgModelVo.getInsertType())) {
                wyArticleImgs.add(articleImgIndex+1,wyArticleImg);
            }
            try {
                //作废历史图片
                wyArticleImgMapper.updateByArticleId(EffectiveType.EFFECTIVE_NO, articleImgModelVo.getArticleId());
            } catch (RuntimeException e) {
                log.error("------------作废历史图片失败！！");
                e.printStackTrace();
            }
            try {
                //插入新的图片信息
                wyArticleImgs.stream().forEach(img -> {
                    img.setId(null);
                    wyArticleImgMapper.insertSelective(img);
                });
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }
    }
    /**
     * 复制文章内容
     * @param articleId 文章id
     */
    @Override
    public void articleCopy(int articleId) {
        WyArticle wyArticle = wyArticleMapper.selectByPrimaryKey(articleId);
        wyArticle.setCreateTime(new Date());
        wyArticle.setUpdateTime(new Date());
        wyArticle.setAuthor(wyArticle.getAuthor()+"复制文章");
        //插入新文章id
        try {
            wyArticleMapper.insertSelective(wyArticle);
        } catch (RuntimeException e) {
            log.error("--------插入新文章失败-----");
            e.printStackTrace();
        }

        WyArticleImgExample wyArticleImgExample = new WyArticleImgExample();
        wyArticleImgExample.createCriteria()
                .andStatusEqualTo(EffectiveType.EFFECTIVE_YES)
                .andArticleIdEqualTo(articleId);
        List<WyArticleImg> wyArticleImgs = wyArticleImgMapper.selectByExample(wyArticleImgExample);
        WyArticle newArticleId = wyArticleMapper.selectLastArticle();
        try {
            wyArticleImgs.stream().forEach(wyArticleImg -> {
                wyArticleImg.setId(null);
                wyArticleImg.setArticleId(newArticleId.getId());
                wyArticleImg.setStatus(EffectiveType.EFFECTIVE_YES);
                wyArticleImg.setCreateTime(new Date());
                wyArticleImg.setUpdateTime(new Date());
                wyArticleImgMapper.insertSelective(wyArticleImg);
            });
        } catch (RuntimeException e) {
            log.error("-------复制文章插入新图片内容失败-----");
            e.printStackTrace();
        }
    }
    /**
     * 删除文章
     * @param articleId 文章id
     */
    @Override
    public void articleDel(int articleId) {
        WyArticle wyArticle = new WyArticle();
        wyArticle.setId(articleId);
        wyArticle.setStatus(EffectiveType.EFFECTIVE_NO);
        try {
            wyArticleMapper.updateByPrimaryKeySelective(wyArticle);
        } catch (Exception e) {
            log.error("------删除文章失败-----");
            e.printStackTrace();
        }
    }
    /**
     * 刷新文章
     */
    public void articleRefresh(int articleId) {
        try {
            redisCache.deleteDataOfRedis(ConstatFinal.AUTHOR+":"+articleId);
            //删除图片相关内容
            redisCache.deleteDataOfRedis(ConstatFinal.IMAGES_LIST+":"+articleId);
            //删除新防封文案相关内容
            redisCache.deleteDataOfRedis(ConstatFinal.NEW_ARTICLE_LIST+":"+articleId);

            redisCache.deleteDataOfRedis(ConstatFinal.IMAGES_CONTENT+":"+articleId);
        } catch (Exception e) {
            log.error("-------文章redis更新");
            e.printStackTrace();
        }
    }
}