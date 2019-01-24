package com.bray.service.wechat.bean;

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
     * 订单号
     */
    public String orderID;
    /**
     * 待付金额
     */
    public String orderMoneySum;
    /**
     * 代填字段
     */
    public String backupFieldName;
    /**
     * 代填字段数据
     */
    public String backupFieldData;
    /**
     * 注释
     */
    public String remark;


    @Override
    public String toString() {
        return "OrderTemplateKeyword{" +
                "first='" + first + '\'' +
                ", orderID='" + orderID + '\'' +
                ", orderMoneySum='" + orderMoneySum + '\'' +
                ", backupFieldName='" + backupFieldName + '\'' +
                ", backupFieldData='" + backupFieldData + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
