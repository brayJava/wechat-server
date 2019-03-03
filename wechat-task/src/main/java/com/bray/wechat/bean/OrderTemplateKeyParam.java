package com.bray.wechat.bean;

import lombok.Data;

/**
 * @Author:wuzhiyuan
 * @Description: 订单模板
 * @Date: Created in 11:07 2019/1/24
 * @Modified By:
 */
@Data
public class OrderTemplateKeyParam {

    /**
     * 详细内容
     */
    public String first;
    /**
     * 功能名称
     */
    public String keyword1;
    /**
     * 失效日期
     */
    public String keyword2;
    /**
    /**
     * 注释
     */
    public String remark;
}
