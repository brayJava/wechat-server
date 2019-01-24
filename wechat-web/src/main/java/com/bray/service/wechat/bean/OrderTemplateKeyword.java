package com.bray.service.wechat.bean;

import lombok.Data;

/**
 * @Author:wuzhiyuan
 * @Description: 提示字段
 * @Date: Created in 11:15 2019/1/24
 * @Modified By:
 */
@Data
public class OrderTemplateKeyword {

    public DataInfo first;

    public DataInfo orderID;

    public DataInfo orderMoneySum;

    public DataInfo backupFieldName;

    public DataInfo backupFieldData;

    public DataInfo remark;

    public OrderTemplateKeyword(DataInfo first, DataInfo orderID, DataInfo orderMoneySum, DataInfo backupFieldName, DataInfo backupFieldData, DataInfo remark) {
        this.first = first;
        this.orderID = orderID;
        this.orderMoneySum = orderMoneySum;
        this.backupFieldName = backupFieldName;
        this.backupFieldData = backupFieldData;
        this.remark = remark;
    }

    public OrderTemplateKeyword() {
    }
}
