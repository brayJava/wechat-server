package com.bray.service;

import com.bray.model.WySafedomain;

/**
 * @Author:wuzhiyuan
 * @Description: 域名管理实现接口
 * @Date: Created in 0:07 2018/9/11
 * @Modified By:
 */
public interface IDomainWebService {
    /**
     * 从redis服务器获取域名
     * @param domainFlag
     * @return
     */
    Object queryDomainByredisServer(String domainFlag,int articleId);
    /**
     * 获取安全域名
     * @return
     */
    WySafedomain getSafeDomain();
}
