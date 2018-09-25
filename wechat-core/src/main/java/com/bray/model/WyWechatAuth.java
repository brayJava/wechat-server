package com.bray.model;

import java.io.Serializable;
import java.util.Date;

public class WyWechatAuth implements Serializable{
    private Integer id;

    private String weixinId;

    private String weixinSecret;

    private String weixinCache;

    private String weixinName;

    private String bindDomains;

    private Boolean debug;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId == null ? null : weixinId.trim();
    }

    public String getWeixinSecret() {
        return weixinSecret;
    }

    public void setWeixinSecret(String weixinSecret) {
        this.weixinSecret = weixinSecret == null ? null : weixinSecret.trim();
    }

    public String getWeixinCache() {
        return weixinCache;
    }

    public void setWeixinCache(String weixinCache) {
        this.weixinCache = weixinCache == null ? null : weixinCache.trim();
    }

    public String getWeixinName() {
        return weixinName;
    }

    public void setWeixinName(String weixinName) {
        this.weixinName = weixinName == null ? null : weixinName.trim();
    }

    public String getBindDomains() {
        return bindDomains;
    }

    public void setBindDomains(String bindDomains) {
        this.bindDomains = bindDomains == null ? null : bindDomains.trim();
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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