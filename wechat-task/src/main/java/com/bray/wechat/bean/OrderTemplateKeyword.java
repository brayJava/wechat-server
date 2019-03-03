package com.bray.wechat.bean;

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

    public DataInfo keyword1;

    public DataInfo keyword2;

    public DataInfo remark;


    @Override
    public String toString() {
        return "OrderTemplateKeyword{" +
                "first=" + first +
                ", keyword1=" + keyword1 +
                ", keyword2=" + keyword2 +
                ", remark=" + remark +
                '}';
    }

    public OrderTemplateKeyword(DataInfo first, DataInfo keyword1, DataInfo keyword2, DataInfo remark) {
        this.first = first;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.remark = remark;
    }

    public OrderTemplateKeyword() {
    }
}
