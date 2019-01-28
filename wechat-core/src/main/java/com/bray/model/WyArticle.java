package com.bray.model;

import java.io.Serializable;
import java.util.Date;

public class WyArticle implements Serializable {

    private static final long serialVersionUID = 6293509963165879323L;
    
    private Integer id;

    private String title;

    private String bgmUrl;

    private String author;

    private String shareTitle;

    private String shareDescribe;

    private String shareImgUrl;

    private Boolean isOrderImg;

    private String dataTransferUrl;

    private String gobackUrl;

    private String readOriginalUrl;

    private String imageGobackUrl;

    private String orderImgUrl;

    private String orderCopyUrl;

    private String shareSuccessFailUrl;

    private String statistical;

    private String connectPrimaryDomain;

    private String connectCommonDomain;

    private Boolean isPublish;

    private Boolean forcedShare;

    private String noShareDomain;

    private String cjContentTemp;

    private Integer userId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private byte[] cjContent;

    public String getCjContentTemp() {
        return cjContentTemp;
    }

    public void setCjContentTemp(String cjContentTemp) {
        this.cjContentTemp = cjContentTemp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getBgmUrl() {
        return bgmUrl;
    }

    public void setBgmUrl(String bgmUrl) {
        this.bgmUrl = bgmUrl == null ? null : bgmUrl.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle == null ? null : shareTitle.trim();
    }

    public String getShareDescribe() {
        return shareDescribe;
    }

    public void setShareDescribe(String shareDescribe) {
        this.shareDescribe = shareDescribe == null ? null : shareDescribe.trim();
    }

    public String getShareImgUrl() {
        return shareImgUrl;
    }

    public void setShareImgUrl(String shareImgUrl) {
        this.shareImgUrl = shareImgUrl == null ? null : shareImgUrl.trim();
    }

    public Boolean getIsOrderImg() {
        return isOrderImg;
    }

    public void setIsOrderImg(Boolean isOrderImg) {
        this.isOrderImg = isOrderImg;
    }

    public String getDataTransferUrl() {
        return dataTransferUrl;
    }

    public void setDataTransferUrl(String dataTransferUrl) {
        this.dataTransferUrl = dataTransferUrl == null ? null : dataTransferUrl.trim();
    }

    public String getGobackUrl() {
        return gobackUrl;
    }

    public void setGobackUrl(String gobackUrl) {
        this.gobackUrl = gobackUrl == null ? null : gobackUrl.trim();
    }

    public String getReadOriginalUrl() {
        return readOriginalUrl;
    }

    public void setReadOriginalUrl(String readOriginalUrl) {
        this.readOriginalUrl = readOriginalUrl == null ? null : readOriginalUrl.trim();
    }

    public String getImageGobackUrl() {
        return imageGobackUrl;
    }

    public void setImageGobackUrl(String imageGobackUrl) {
        this.imageGobackUrl = imageGobackUrl == null ? null : imageGobackUrl.trim();
    }

    public String getOrderImgUrl() {
        return orderImgUrl;
    }

    public void setOrderImgUrl(String orderImgUrl) {
        this.orderImgUrl = orderImgUrl == null ? null : orderImgUrl.trim();
    }

    public String getOrderCopyUrl() {
        return orderCopyUrl;
    }

    public void setOrderCopyUrl(String orderCopyUrl) {
        this.orderCopyUrl = orderCopyUrl == null ? null : orderCopyUrl.trim();
    }

    public String getShareSuccessFailUrl() {
        return shareSuccessFailUrl;
    }

    public void setShareSuccessFailUrl(String shareSuccessFailUrl) {
        this.shareSuccessFailUrl = shareSuccessFailUrl == null ? null : shareSuccessFailUrl.trim();
    }

    public String getStatistical() {
        return statistical;
    }

    public void setStatistical(String statistical) {
        this.statistical = statistical == null ? null : statistical.trim();
    }

    public String getConnectPrimaryDomain() {
        return connectPrimaryDomain;
    }

    public void setConnectPrimaryDomain(String connectPrimaryDomain) {
        this.connectPrimaryDomain = connectPrimaryDomain == null ? null : connectPrimaryDomain.trim();
    }

    public String getConnectCommonDomain() {
        return connectCommonDomain;
    }

    public void setConnectCommonDomain(String connectCommonDomain) {
        this.connectCommonDomain = connectCommonDomain == null ? null : connectCommonDomain.trim();
    }

    public Boolean getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Boolean isPublish) {
        this.isPublish = isPublish;
    }

    public Boolean getForcedShare() {
        return forcedShare;
    }

    public void setForcedShare(Boolean forcedShare) {
        this.forcedShare = forcedShare;
    }

    public String getNoShareDomain() {
        return noShareDomain;
    }

    public void setNoShareDomain(String noShareDomain) {
        this.noShareDomain = noShareDomain == null ? null : noShareDomain.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public byte[] getCjContent() {
        return cjContent;
    }

    public void setCjContent(byte[] cjContent) {
        this.cjContent = cjContent;
    }
}