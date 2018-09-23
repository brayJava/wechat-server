package com.bray.model.Vo;

import lombok.Data;

/**
 * @Author:wuzhiyuan
 * @Description: 域名添加类
 * @Date: Created in 15:05 2018/9/1
 * @Modified By:
 */
@Data
public class DomainModelVo {

    private int domainId;

    private String domain;

    private String domainFrom;

    private String domainType;

    private String articleId;
}
