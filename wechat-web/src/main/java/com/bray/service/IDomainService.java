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
    Object queryAllEffectiveDomain();

}
