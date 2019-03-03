package com.bray.wechat.bean;

/**
 * @author:wuzhiyuan
 * @description: 模板参数
 * @date: Created in 17:47 2018/5/28
 * @modified By:
 */
public class TemplateParam {
    /**
     * 标题
     */
    private String title;
    /**
     * 通知时间
     */
    private String noticeTime;
    /**
     * 通知内容
     */
    private String noticeContent;
    /**
     * 标记内容
     */
    private String remark;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public TemplateParam() {
    }

    public TemplateParam(String title, String noticeTime, String noticeContent, String remark) {
        this.title = title;
        this.noticeTime = noticeTime;
        this.noticeContent = noticeContent;
        this.remark = remark;
    }
}
