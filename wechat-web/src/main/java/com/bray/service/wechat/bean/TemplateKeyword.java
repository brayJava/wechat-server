package com.bray.service.wechat.bean;

/**
 * @author:wuzhiyuan
 * @description:
 * @date: Created in 16:53 2018/5/25
 * @modified By:
 */
public class TemplateKeyword {

    private DataInfo first;

    private DataInfo keyword1;

    private DataInfo keyword2;

    private DataInfo remark;

    public TemplateKeyword() {
    }
    public TemplateKeyword(DataInfo first, DataInfo keyword1, DataInfo keyword2, DataInfo remark) {
        this.first = first;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.remark = remark;
    }

    public DataInfo getFirst() {
        return first;
    }

    public void setFirst(DataInfo first) {
        this.first = first;
    }

    public DataInfo getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(DataInfo keyword1) {
        this.keyword1 = keyword1;
    }

    public DataInfo getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(DataInfo keyword2) {
        this.keyword2 = keyword2;
    }

    public DataInfo getRemark() {
        return remark;
    }

    public void setRemark(DataInfo remark) {
        this.remark = remark;
    }
}
