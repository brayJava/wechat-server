package com.bray.service;

/**
 * @Author:wuzhiyuan
 * @Description: 域名相关接口
 * @Date: Created in 15:20 2018/9/1
 * @Modified By:
 */
public interface IDomainAdminService {
    /**
     * 添加域名
     */
    void domainAdd(Object domain);
    /**
     * 子域名添加
     */
    void subdomainAdd(Object subdomain);
    /**
     * 修改域名
     * @param id   域名id
     */
    void deleteDomain(int id);
    /**
     * 修改子域名
     * @param id   域名id
     */
    void deleteSubdomain(int id);
}
