package com.bray.wechat.bean;

/**
 * @author:wuzhiyuan
 * @description: 微信消息模板数据
 * @date: Created in 10:13 2018/5/10
 * @modified By:
 */
public class WechatMsgInfo {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 患者
     */
    private String patient;
    /**
     * 疑似疾病
     */
    private String illness;
    /**
     * 约诊费用
     */
    private String appointmentFee;
    /**
     * 链接
     */
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getAppointmentFee() {
        return appointmentFee;
    }

    public void setAppointmentFee(String appointmentFee) {
        this.appointmentFee = appointmentFee;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
