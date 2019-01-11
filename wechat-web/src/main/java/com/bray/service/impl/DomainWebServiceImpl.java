package com.bray.service.impl;

import com.bray.aop.annotation.QueryCache;
import com.bray.config.WebConst;
import com.bray.dto.ConstFinal;
import com.bray.dto.ConstatFinal;
import com.bray.mapper.WySafedomainMapper;
import com.bray.model.Bo.PrimarySubDomain;
import com.bray.model.WyDomain;
import com.bray.model.WySafedomain;
import com.bray.model.WySubdomain;
import com.bray.model.enums.DomainType;
import com.bray.service.IDomainService;
import com.bray.service.IDomainWebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:wuzhiyuan
 * @Description: 域名管理实现类
 * @Date: Created in 0:09 2018/9/11
 * @Modified By:
 */
@Slf4j
@Service
public class DomainWebServiceImpl implements IDomainWebService{

    private Map<String,Object> domainMap = new HashMap<>();

    @Autowired
    private IDomainService domainService;
    @Autowired
    private WySafedomainMapper wySafedomainMapper;
    /**
     * redis缓存域名信息，
     * @param domainFlag
     * @return
     */
    @Override
    @QueryCache
    public Object queryDomainByredisServer(String domainFlag,int articleId) {
        //获取所有有效域名域名
        List<PrimarySubDomain> allEffectiveDomain =
                (List<PrimarySubDomain>) domainService.queryAllEffectiveDomain(ConstatFinal.QUERY_WEB,articleId);
        List<WySubdomain> withoutShareDomains = new ArrayList<>();
        List<WySubdomain> shareDomains = new ArrayList<>();
        List<WySubdomain> commonDomains = new ArrayList<>();
        if(!CollectionUtils.isEmpty(allEffectiveDomain)) {
            allEffectiveDomain.forEach(primarySubDomain -> {
                String type = primarySubDomain.getWyDomain().getType();
                //盘判断是否是分享主域名
                if(DomainType.SHAREDOMAIN.getType().equals(type)) {
                    WySubdomain wySubdomain = getWySubdomain(primarySubDomain);
                    shareDomains.add(wySubdomain);
                    //将可分享主域名的子域名都添加到缓存中
                    shareDomains.addAll(primarySubDomain.getWySubdomainList());
                }else if(DomainType.COMMONDOMAIN.getType().equals(type)) {
                    WySubdomain wySubdomain = getWySubdomain(primarySubDomain);
                    commonDomains.add(wySubdomain);
                    //将内容相关的子域名放入缓存中
                    commonDomains.addAll(primarySubDomain.getWySubdomainList());
                } else if (DomainType.WITHOUT_SHARE_DOMAIN.getType().equals(type)) {
                    WySubdomain wySubdomain = getWySubdomain(primarySubDomain);
                    withoutShareDomains.add(wySubdomain);
                    withoutShareDomains.addAll(primarySubDomain.getWySubdomainList());
                }
            });
            this.domainMap.put(WebConst.SUB_SHARE_DOMAIN,shareDomains);
            this.domainMap.put(WebConst.SUB_COMMON_DOMAIN,commonDomains);
            this.domainMap.put(WebConst.RONDOM_COMMON_DOMAIN,withoutShareDomains);
        }
        //将所有有效域名方法缓存中
        this.domainMap.put(WebConst.ALL_DOMAIN,allEffectiveDomain);
        log.info("【----------------------域名缓存完毕------------------】");
        return domainMap;
    }
    /**
     *
     * @param primarySubDomain
     * @return
     */
    private WySubdomain getWySubdomain(PrimarySubDomain primarySubDomain) {
        WySubdomain  wySubdomain = new WySubdomain();
        String wyDomain = primarySubDomain.getWyDomain().getDomain();
        if(wyDomain.indexOf("www") > 0 ) {
            wySubdomain.setSubDomain(wyDomain);
        } else {
            wySubdomain.setSubDomain("www."+wyDomain);
        }
        return wySubdomain;
    }

    /**
     * 获取安全域名
     * @return
     */
    public WySafedomain getSafeDomain() {
        WySafedomain wySafedomain = wySafedomainMapper.selectByPrimaryKey(1);
        return wySafedomain;
    }
}
