package com.bray.model.Vo;

import lombok.Data;

/**
 * @Author:wuzhiyuan
 * @Description: 子域名类添加
 * @Date: Created in 13:10 2018/9/3
 * @Modified By:
 */

@Data
public class SubDomainModelVo {

    public int domainId;

    public String subdomain;

    public int subdomainId;

    public String domainType;
}
