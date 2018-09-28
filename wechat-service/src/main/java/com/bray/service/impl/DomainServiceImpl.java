package com.bray.service.impl;

import com.bray.dto.ConstatFinal;
import com.bray.dto.EffectiveType;
import com.bray.mapper.WyWechatAuthMapper;
import com.bray.model.*;
import com.bray.model.Bo.PrimarySubDomain;
import com.bray.model.enums.DomainType;
import com.bray.service.IDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author:wuzhiyuan
 * @Description: 域名操作实现
 * @Date: Created in 10:06 2018/8/28
 * @Modified By:
 */
@Slf4j
@Service
public class DomainServiceImpl extends DomainBaseService
        implements IDomainService<Object>{

    @Resource
    private WyWechatAuthMapper wyWechatAuthMapper;
    /**
     * 查询主域名
     * @return
     */
    @Override
    public List<Object> queryPrimaryDomain() {
        WyDomainExample wyDomainExample = new WyDomainExample();
        wyDomainExample.createCriteria().andTypeEqualTo(DomainType.LOCALDOMAIN.getType());
        List wyDomains = wyDomainMapper.selectByExample(wyDomainExample);
        return wyDomains;
    }
    /**
     *查询所有有效域名{“主域名”：{"主域名"：primaryDomain,[{"子域名"：subDomain}]}}
     * @return
     */
    public Object queryAllEffectiveDomain(String queryType,String articleId) {
        WyDomainExample wyDomainExample = new WyDomainExample();
        //如果是admin查询则返回所有没被删除的
        if(ConstatFinal.QUERY_ADMIN.equals(queryType))
             wyDomainExample.createCriteria().andIsDelEqualTo(EffectiveType.EFFECTIVE_YES);
        //如果是web查询则返回所有有效且没被删除的
        if(ConstatFinal.QUERY_WEB.equals(queryType) && !StringUtils.isEmpty(articleId)) {
            wyDomainExample.createCriteria()
                    .andIsDelEqualTo(EffectiveType.EFFECTIVE_YES)
                    .andStatusEqualTo(EffectiveType.EFFECTIVE_YES)
                    // .andArticleIdEqualTo(articleId)
                    .andArticleIdLike(articleId);
        }
        wyDomainExample.setOrderByClause("create_time desc");
        List<WyDomain> wyDomains = wyDomainMapper.selectByExample(wyDomainExample);
        List<PrimarySubDomain> primarySubDomainList = new ArrayList<>();
        if( !CollectionUtils.isEmpty(wyDomains)) {
            wyDomains.forEach(wyDomain -> {
                final WySubdomainExample wySubdomainExample = new WySubdomainExample();
                wySubdomainExample.createCriteria()
                        .andStatusEqualTo(EffectiveType.EFFECTIVE_YES)
                        .andDomainIdEqualTo(wyDomain.getId());
                wySubdomainExample.setOrderByClause(" create_time desc ");
                List<WySubdomain> wySubdomains = wySubdomainMapper.selectByExample(wySubdomainExample);
                PrimarySubDomain primarySubDomain = PrimarySubDomain.builder()
                        .wyDomain(wyDomain).wySubdomainList(wySubdomains).build();
                primarySubDomainList.add(primarySubDomain);
            });
        }
        return primarySubDomainList;
    }

    /**
     * 查询域名并根据公众号进行分类
     * @param queryType
     * @param articleId
     * @return
     */
    public Object queryPrimarySubDomainMap(String queryType,String articleId) {

        Map<String,Object> primarySubMap = new LinkedHashMap<>();
        List<PrimarySubDomain> primarySubDomainList =
                (List<PrimarySubDomain>)this.queryAllEffectiveDomain(queryType, articleId);
        primarySubDomainList.forEach(primarySubDomain -> {
            String key = primarySubDomain.getWyDomain().getAppId();
            if(StringUtils.isEmpty(key)) {
                key = "非公众号域名";
            } else {
                //查询公众号相关内容
                WyWechatAuthExample wyWechatAuthExample = new WyWechatAuthExample();
                wyWechatAuthExample.createCriteria().andWeixinIdEqualTo(key);
                List<WyWechatAuth> wyWechatAuths = wyWechatAuthMapper.selectByExample(wyWechatAuthExample);
                if(!CollectionUtils.isEmpty(wyWechatAuths)) {
                    WyWechatAuth wyWechatAuth = wyWechatAuths.get(0);
                    key = new StringBuilder().append(wyWechatAuth.getWeixinId()).append("--")
                            .append(wyWechatAuth.getWeixinName()).toString();
                }
            }
            if(Objects.isNull(primarySubMap.get(key))) {
                List<PrimarySubDomain> list = new ArrayList<>();
                list.add(primarySubDomain);
                primarySubMap.put(key,list);
            } else {
                List<PrimarySubDomain> primarySubDomains = (List<PrimarySubDomain>)primarySubMap.get(key);
                primarySubDomains.add(primarySubDomain);
                primarySubMap.put(key,primarySubDomains);
            }
        });
        return primarySubMap;
    }

}