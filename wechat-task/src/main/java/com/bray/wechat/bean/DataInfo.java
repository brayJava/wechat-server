package com.bray.wechat.bean;

/**
 * @author:wuzhiyuan
 * @description:
 * @date: Created in 10:08 2018/5/10
 * @modified By:
 */
public class DataInfo {
    /**
     *  数据内容
     */
    private String value;
    /**
     * 颜色
     */
    private String color;

    public DataInfo() {
    }

    public DataInfo(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
