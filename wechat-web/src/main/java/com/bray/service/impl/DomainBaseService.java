package com.bray.service.impl;

import com.bray.mapper.WyDomainMapper;
import com.bray.mapper.WySubdomainMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 10:08 2018/8/28
 * @Modified By:
 */
@Service
public class DomainBaseService {

    @Resource
    protected WyDomainMapper wyDomainMapper;
    @Resource
    protected WySubdomainMapper wySubdomainMapper;
}
