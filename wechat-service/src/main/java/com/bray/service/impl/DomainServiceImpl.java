package com.bray.service.impl;

import com.bray.dto.EffectiveType;
import com.bray.model.Bo.PrimarySubDomain;
import com.bray.model.WyDomain;
import com.bray.model.WyDomainExample;
import com.bray.model.WySubdomain;
import com.bray.model.WySubdomainExample;
import com.bray.model.enums.DomainType;
import com.bray.service.IDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
    public Object queryAllEffectiveDomain() {
        WyDomainExample wyDomainExample = new WyDomainExample();
        wyDomainExample.createCriteria().andIsDelEqualTo(EffectiveType.EFFECTIVE_YES);
        wyDomainExample.setOrderByClause("update_time desc");
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
                PrimarySubDomain primarySubDomain = PrimarySubDomain.builder().wyDomain(wyDomain).wySubdomainList(wySubdomains).build();
                primarySubDomainList.add(primarySubDomain);
            });
        }
        return primarySubDomainList;
    }
}