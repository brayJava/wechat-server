package com.bray.model.enums;

/**
 * @Author:wuzhiyuan
 * @Description: 域名枚举，1，可分享主域名 2，任意主域名 3，本地主域名
 * @Date: Created in 10:24 2018/8/28
 * @Modified By:
 */
public enum DomainType {

    SHAREDOMAIN("1"),

    COMMONDOMAIN("2"),

    LOCALDOMAIN("3");

    private final String type;

    DomainType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
