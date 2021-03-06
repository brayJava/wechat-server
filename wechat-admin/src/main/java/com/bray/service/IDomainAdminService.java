package com.bray.service;

import com.bray.model.WyDomain;
import com.bray.model.WySafedomain;

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
    /**
     * 更新线上域名信息
     */
    void updateProd();
    /**
     * 更新域名状态
     */
    void updateStatus(int status,int domainId);
//     /**
//      * 通过域名id查询对应文章id
//      * @param id
//      * @return
//      */
//     void refreshDomainByArticleId(int id);
    void updateSafeDomain(String safeDomain,String mail);

    WySafedomain querySafeDomain();
 }
