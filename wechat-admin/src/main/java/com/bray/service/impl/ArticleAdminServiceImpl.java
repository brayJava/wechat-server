package com.bray.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bray.aop.cache.RedisPoolCache;
import com.bray.config.MemcachedRunner;
import com.bray.dto.ConstFinal;
import com.bray.dto.ConstatFinal;
import com.bray.dto.EffectiveType;
import com.bray.dto.InsertType;
import com.bray.mapper.WyArticleImgMapper;
import com.bray.mapper.WyArticleMapper;
import com.bray.mapper.WySubImgMapper;
import com.bray.model.*;
import com.bray.model.Bo.ArticleImgModelVo;
import com.bray.model.Vo.ArticleModelVo;
import com.bray.model.Vo.ArticleOtherModelVo;
import com.bray.service.IArticleAdminService;
import com.bray.util.GUIDUtil;
import com.bray.util.TStringUtil;
import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialBlob;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.time.Clock;
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
    private WySubImgMapper wySubImgMapper;
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
        //是否是h5平台
        wyArticle.setIsPublish(ConstFinal.SHARE_STATUS.equals(articleOtherModelVo.getHcj()) ? true : false);
        //按字符逗号隔开格式存入
        wyArticle.setNoShareDomain(TStringUtil.dealStr(articleOtherModelVo.getNoShareDomain()));
        wyArticle.setUserId(articleOtherModelVo.getUserId());
        // if(!StringUtils.isEmpty(articleOtherModelVo.getCjContent()))
        //     wyArticle.setCjContent(articleOtherModelVo.getCjContent().getBytes());
        redisCache.saveDataToRedis(ConstatFinal.ARTICLE_H5_2_VAL+":"+articleOtherModelVo.getArticleId(),articleOtherModelVo.getCjContent());
        wyArticle.setStatus(EffectiveType.EFFECTIVE_YES);
        wyArticle.setCreateTime(new Date());
        wyArticle.setUpdateTime(new Date());
        try {
            wyArticleMapper.insertSelective(wyArticle);
        } catch (RuntimeException e) {
            log.error("-----------文章添加失败----------");
            e.printStackTrace();
        }
        insertNewArticleImages(wyArticle.getId(), articleOtherModelVo);
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
        //是否是h5平台
        wyArticle.setIsPublish(ConstFinal.SHARE_STATUS.equals(articleOtherModelVo.getHcj()) ? true : false);
        wyArticle.setNoShareDomain(TStringUtil.dealStr(articleOtherModelVo.getNoShareDomain()));
        wyArticle.setUserId(articleOtherModelVo.getUserId());
        // if(!StringUtils.isEmpty(articleOtherModelVo.getCjContent()))
        //     wyArticle.setCjContent(articleOtherModelVo.getCjContent().getBytes());
        wyArticle.setUpdateTime(new Date());
        //将h5场景内容放入redis中
        redisCache.updateDataOfRedis(ConstatFinal.ARTICLE_H5_2_VAL+":"+articleOtherModelVo.getArticleId(),articleOtherModelVo.getCjContent());
        this.articleRefresh(articleOtherModelVo.getArticleId(),"");
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
    // /**
    //  * 刷新文章
    //  */
    // public void articleRefresh(String articleId) {
    //     try {
    //         redisCache.deleteDataOfRedis(ConstatFinal.AUTHOR+":"+articleId);
    //         //删除图片相关内容
    //         redisCache.deleteDataOfRedis(ConstatFinal.IMAGES_LIST+":"+articleId);
    //         //删除新防封文案相关内容
    //         redisCache.deleteDataOfRedis(ConstatFinal.NEW_ARTICLE_LIST+":"+articleId);
    //
    //         redisCache.deleteDataOfRedis(ConstatFinal.IMAGES_CONTENT+":"+articleId);
    //     } catch (Exception e) {
    //         log.error("-------文章redis更新");
    //         e.printStackTrace();
    //     }
    // }
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
                for(int i = 0; i<3 ; i++) {
                    WySubImg wySubImg = new WySubImg();
                    wySubImg.setImgId(wyArticleImg.getId());
                    wySubImg.setBgUrl("");
                    wySubImg.setCreateDate(new Date());
                    wySubImg.setUpdateDate(new Date());
                    wySubImgMapper.insertSelective(wySubImg);
                }
            } catch (RuntimeException e) {
                log.error("-----------关联图片添加失败----------");
                e.printStackTrace();
            }
        });
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
    public void updateArticleImg(int articleImgId, String imgUrl, String imgUrlHref,String subimgs) {
        WyArticleImg wyArticleImg = new WyArticleImg();
        wyArticleImg.setId(articleImgId);
        wyArticleImg.setThirdImgPath(imgUrl);
        wyArticleImg.setImgUrl(imgUrlHref);
        try {
            wyArticleImgMapper.updateByPrimaryKeySelective(wyArticleImg);
            //修改子图
            if(!StringUtils.isEmpty(subimgs)) {
                String[] subs = subimgs.split(",");
                for(String subimg : subs) {
                    String[] splits = subimg.split("!");
                    WySubImg wySubImg = new WySubImg();
                    wySubImg.setId(Integer.valueOf(splits[1]));
                    wySubImg.setBgUrl(splits[0]);
                    wySubImg.setUpdateDate(new Date());
                    wySubImgMapper.updateByPrimaryKeySelective(wySubImg);
                }
            }

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
        log.info("文章id articleId" + articleId);
        wyArticle.setId(null);
        wyArticle.setCreateTime(new Date());
        wyArticle.setUpdateTime(new Date());
        wyArticle.setAuthor(wyArticle.getAuthor()+"-复制");
        String swyArticle = JSON.toJSONString(wyArticle);
        log.info("文章json："+swyArticle);
        //插入新文章id
        try {
            if(!Objects.isNull(wyArticle.getId())) wyArticle.setId(null);
            wyArticleMapper.insertSelective(wyArticle);
            log.info("文章id："+wyArticle.getId());
        } catch (RuntimeException e) {
            log.error("--------插入新文章失败-----");
            e.printStackTrace();
        }

        WyArticleImgExample wyArticleImgExample = new WyArticleImgExample();
        wyArticleImgExample.createCriteria()
                .andStatusEqualTo(EffectiveType.EFFECTIVE_YES)
                .andArticleIdEqualTo(articleId);
        List<WyArticleImg> wyArticleImgs = wyArticleImgMapper.selectByExample(wyArticleImgExample);
        try {
            wyArticleImgs.stream().forEach(wyArticleImg -> {
                WyArticleImg wyArticleImgnew = new WyArticleImg();
                wyArticleImgnew.setArticleId(wyArticle.getId());
                wyArticleImgnew.setStatus(EffectiveType.EFFECTIVE_YES);
                wyArticleImgnew.setCreateTime(new Date());
                wyArticleImgnew.setUpdateTime(new Date());
                wyArticleImgnew.setThirdImgPath(wyArticleImg.getThirdImgPath());
                wyArticleImgnew.setImgUrl(wyArticleImg.getImgUrl());
                wyArticleImgMapper.insertSelective(wyArticleImgnew);
                WySubImgExample wySubImgExample = new WySubImgExample();
                wySubImgExample.createCriteria().andImgIdEqualTo(wyArticleImg.getId());
                List<WySubImg> wySubImgs = wySubImgMapper.selectByExample(wySubImgExample);
                if(CollectionUtils.isEmpty(wySubImgs)) {
                    for(int i = 0; i<3 ; i++) {
                        WySubImg wySubImg = new WySubImg();
                        wySubImg.setImgId(wyArticleImgnew.getId());
                        wySubImg.setBgUrl("");
                        wySubImg.setCreateDate(new Date());
                        wySubImg.setUpdateDate(new Date());
                        wySubImgMapper.insertSelective(wySubImg);
                    }
                } else {
                    wySubImgs.stream().forEach(wySubImg -> {
                        WySubImg subimg = new WySubImg();
                        subimg.setImgId(wyArticleImgnew.getId());
                        subimg.setBgUrl(wySubImg.getBgUrl());
                        subimg.setCreateDate(new Date());
                        subimg.setUpdateDate(new Date());
                        wySubImgMapper.insertSelective(subimg);

                    });
                }

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
    public void articleRefresh(int articleId,String othervalue) {
        try {
            redisCache.deleteDataOfRedis(ConstatFinal.AUTHOR+":"+articleId);
            //删除图片相关内容
            redisCache.deleteDataOfRedis(ConstatFinal.IMAGES_LIST+":"+articleId);
            //删除新防封文案相关内容
            redisCache.deleteDataOfRedis(ConstatFinal.NEW_ARTICLE_LIST+":"+articleId);

            redisCache.deleteDataOfRedis(ConstatFinal.IMAGES_CONTENT+":"+articleId);

            redisCache.deleteDataOfRedis(ConstatFinal.ARTICLE_CONTENT+":"+articleId);

            redisCache.deleteDataOfRedis(ConstatFinal.ARTICLENEW_CONTENT+":"+articleId);

            redisCache.deleteDataOfRedis(ConstatFinal.IFRAMELIST+":"+articleId);

            redisCache.deleteDataOfRedis("limitme_list:"+articleId);

            redisCache.deleteDataOfRedis("wmjm_content:"+articleId);

            redisCache.deleteDataOfRedis("zxplat_content:"+articleId);

            redisCache.deleteDataOfRedis("wd_1_content:"+articleId);

            redisCache.deleteDataOfRedis("wd_1_article:"+articleId);

            redisCache.deleteDataOfRedis("wd_2_content:"+articleId);

            redisCache.deleteDataOfRedis("wd_2_article:"+articleId);

            redisCache.deleteDataOfRedis("wd_3_content:"+articleId);

            redisCache.deleteDataOfRedis("wd_3_article:"+articleId);

            redisCache.deleteDataOfRedis(ConstatFinal.ORDERVAL99);

            redisCache.deleteDataOfRedis(ConstatFinal.ARTICLE_H5_1+":"+articleId);

            redisCache.deleteDataOfRedis(ConstatFinal.ARTICLE_H5_2+":"+articleId);

            redisCache.deleteDataOfRedis(ConstatFinal.ALL_ARTICLES);
            } catch (Exception e) {
            log.error("-------文章redis更新");
            e.printStackTrace();
        }
    }
}