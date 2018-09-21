package com.bray.service;

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
    Object queryDomainByredisServer(String domainFlag,String articleId);
}
