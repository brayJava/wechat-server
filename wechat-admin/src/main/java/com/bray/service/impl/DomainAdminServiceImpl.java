package com.bray.service.impl;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.dto.ConstatFinal;
import com.bray.dto.EffectiveType;
import com.bray.mapper.WyDomainMapper;
import com.bray.mapper.WySafedomainMapper;
import com.bray.mapper.WySubdomainMapper;
import com.bray.model.Vo.DomainModelVo;
import com.bray.model.Vo.SubDomainModelVo;
import com.bray.model.WyDomain;
import com.bray.model.WySafedomain;
import com.bray.model.WySubdomain;
import com.bray.service.IDomainAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @Author:wuzhiyuan
 * @Description: 域名处理逻辑
 * @Date: Created in 15:21 2018/9/1
 * @Modified By:
 */
@Slf4j
@Service
@Transactional
public class DomainAdminServiceImpl implements IDomainAdminService {
    @Resource
    private WyDomainMapper wyDomainMapper;
    @Resource
    private WySubdomainMapper wySubdomainMapper;
    @Resource
    private WySafedomainMapper wySafedomainMapper;
    @Resource
    private RedisPoolCache redisCache;
    /**
     * 主域名添加
     * @param domain
     */
    @Override
    public void domainAdd(Object domain) {
        DomainModelVo domainModelVo = (DomainModelVo) domain;
        WyDomain wyDomain = new WyDomain();
        if(domainModelVo.getDomainId() != 0 ) {
            wyDomain.setId(domainModelVo.getDomainId());
            wyDomain.setType(domainModelVo.getDomainType());
            wyDomain.setDomain(domainModelVo.getDomain());
            wyDomain.setDomainFrom(domainModelVo.getDomainFrom());
            wyDomain.setArticleId(domainModelVo.getArticleId());
            wyDomain.setAppId(domainModelVo.getAppId());
            wyDomain.setUpdateTime(new Date());
            try {
                wyDomainMapper.updateByPrimaryKeySelective(wyDomain);
            } catch (Exception e) {
                log.error("----------域名更新失败----------");
                e.printStackTrace();
            }
        } else {
            wyDomain.setDomain(domainModelVo.getDomain());
            wyDomain.setDomainFrom(domainModelVo.getDomainFrom());
            wyDomain.setStatus(EffectiveType.EFFECTIVE_YES);
            wyDomain.setType(domainModelVo.getDomainType());
            wyDomain.setAppId(domainModelVo.getAppId());
            wyDomain.setArticleId(domainModelVo.getArticleId());
            wyDomain.setCreateTime(new Date());
            wyDomain.setUpdateTime(new Date());
            try {
                wyDomainMapper.insertSelective(wyDomain);
            } catch (RuntimeException e) {
                log.error("----------域名添加失败----------");
                e.printStackTrace();
            }
        }
    }
    /**
     * 子域名添加/更新
     * @param subdomain
     */
    @Override
    public void subdomainAdd(Object subdomain) {
        SubDomainModelVo subDomainModelVo = (SubDomainModelVo) subdomain;
        WySubdomain wySubdomain = new WySubdomain();
        if(subDomainModelVo.domainId == 0) {
            wySubdomain.setId(subDomainModelVo.getSubdomainId());
            wySubdomain.setSubDomain(subDomainModelVo.getSubdomain());
            wySubdomain.setType(subDomainModelVo.getDomainType());
            try {
                wySubdomainMapper.updateByPrimaryKeySelective(wySubdomain);
            } catch (RuntimeException e) {
                log.error("----------子域名更新失败----------");
                e.printStackTrace();
            }
        } else {
            wySubdomain.setDomainId(subDomainModelVo.getDomainId());
            wySubdomain.setSubDomain(subDomainModelVo.getSubdomain());
            wySubdomain.setType(subDomainModelVo.getDomainType());
            wySubdomain.setStatus(EffectiveType.EFFECTIVE_YES);
            wySubdomain.setCreateTime(new Date());
            wySubdomain.setUpdateTime(new Date());
            try {
                wySubdomainMapper.insertSelective(wySubdomain);
            } catch (RuntimeException e) {
                log.error("----------子域名添加失败----------");
                e.printStackTrace();
            }
        }
    }
    /**
     * 修改主域名
     * @param id   域名id
     */
    @Override
    public void deleteDomain(int id) {
        WyDomain wyDomain = new WyDomain();
        wyDomain.setId(id);
        wyDomain.setIsDel(EffectiveType.EFFECTIVE_NO);
        try {
            wyDomainMapper.updateByPrimaryKeySelective(wyDomain);
        } catch (RuntimeException e) {
            log.error("----------删除域名失败----------");
            e.printStackTrace();
        }
    }
    /**
     * 修改子域名
     * @param id   域名id
     */
    @Override
    public void deleteSubdomain(int id) {
        WySubdomain wySubdomain = new WySubdomain();
        wySubdomain.setId(id);
        wySubdomain.setStatus(EffectiveType.EFFECTIVE_NO);
        try {
            wySubdomainMapper.updateByPrimaryKeySelective(wySubdomain);
        } catch (RuntimeException e) {
            log.error("----------删除子域名失败----------");
            e.printStackTrace();
        }
    }
    /**
     * 更新线上域名信息
     */
    @Override
    public void updateProd() {
        try {
            redisCache.deleteDataOfRedis(ConstatFinal.DOMAIN_MAP);
        } catch (Exception e) {
            log.error("------redis key({})删除失败",ConstatFinal.DOMAIN_MAP);
            e.printStackTrace();
        }
    }
    /**
     * 更新线上域名信息
     */
    @Override
    public void updateStatus(int status,int domainId) {
        WyDomain wyDomain = new WyDomain();
        wyDomain.setId(domainId);
        wyDomain.setStatus(status);
        wyDomain.setUpdateTime(new Date());
        try {
            wyDomainMapper.updateByPrimaryKeySelective(wyDomain);
        } catch (Exception e) {
            log.error("----------域名状态更新失败");
            e.printStackTrace();
        }
    }
    /**
     * 更新安全域名
     * @param safeDomain
     */
    public void updateSafeDomain(String safeDomain,String mail) {
        WySafedomain wySafedomain = new WySafedomain();
        wySafedomain.setId(1);
        wySafedomain.setSafeUrl(safeDomain);
        wySafedomain.setMail(mail);
        wySafedomain.setUpdateTime(new Date());
        try {
            wySafedomainMapper.updateByPrimaryKeySelective(wySafedomain);
        } catch (Exception e) {
            log.error("----------安全域名更新失败");
            e.printStackTrace();
        }
    }

    @Override
    public WySafedomain querySafeDomain() {

        WySafedomain wySafedomain = wySafedomainMapper.selectByPrimaryKey(1);
        return wySafedomain;
    }
// /**
    //  * 通过域名id更新对应文章域名域
    //  * @param id 域名id8
    //  */
    // @Override
    // public void refreshDomainByArticleId(int id) {
    //     WyDomain wyDomain = wyDomainMapper.selectByPrimaryKey(id);
    //     if(!Objects.isNull(wyDomain)) {
    //         if(!StringUtils.isEmpty(wyDomain.getArticleId())) {
    //             try {
    //                 String[] articleIds = wyDomain.getArticleId().split(",");
    //                 if(articleIds == null || articleIds.length == 0) {
    //                     articleIds = wyDomain.getArticleId().split("，");
    //                 }
    //                 Arrays.stream(articleIds).forEach(articleId -> {
    //                     redisCache.deleteDataOfRedis(ConstatFinal.DOMAIN_MAP+"_"+articleId.trim());
    //                 });
    //             } catch (Exception e) {
    //                 log.error("------redis key({})删除失败",ConstatFinal.DOMAIN_MAP+"_"+wyDomain.getArticleId());
    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    //
    // }
}
