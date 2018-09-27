package com.bray.service;

import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 域名获取接口
 * @Date: Created in 10:02 2018/8/28
 * @Modified By:
 */
public interface IDomainService<T> {
    /**
     * 查询主域名
     * @return
     */
    List<T> queryPrimaryDomain();
    /**
     * 查询所有有效的域名
     * @return
     */
    Object queryAllEffectiveDomain(String ServiceType,String articleId);
    /**
     * 查询域名并根据公众号进行分类
     * @param queryType 查询方式，来自web/admin
     * @param articleId 文章id
     * @return
     */
    Object queryPrimarySubDomainMap(String queryType,String articleId);
}
