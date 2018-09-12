package com.bray.service.impl;

import com.bray.aop.cache.RedisCache;
import com.bray.dto.ConstatFinal;
import com.bray.dto.EffectiveType;
import com.bray.mapper.WyDomainMapper;
import com.bray.mapper.WySubdomainMapper;
import com.bray.model.Vo.DomainModelVo;
import com.bray.model.Vo.SubDomainModelVo;
import com.bray.model.WyDomain;
import com.bray.model.WySubdomain;
import com.bray.service.IDomainAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

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
    private RedisCache redisCache;
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
            try {
                wyDomainMapper.updateByPrimaryKeySelective(wyDomain);
                log.error("----------域名更新失败----------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            wyDomain.setDomain(domainModelVo.getDomain());
            wyDomain.setDomainFrom(domainModelVo.getDomainFrom());
            wyDomain.setStatus(EffectiveType.EFFECTIVE_YES);
            wyDomain.setType(domainModelVo.getDomainType());
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
        wyDomain.setStatus(EffectiveType.EFFECTIVE_NO);
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

}
