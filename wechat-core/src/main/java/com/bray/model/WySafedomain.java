package com.bray.model;

import java.io.Serializable;
import java.util.Date;

public class WySafedomain implements Serializable{
    private Integer id;

    private String safeUrl;

    private String mail;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSafeUrl() {
        return safeUrl;
    }

    public void setSafeUrl(String safeUrl) {
        this.safeUrl = safeUrl == null ? null : safeUrl.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}