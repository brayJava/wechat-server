package com.bray.excel.modules;


import com.bray.excel.annotation.ExcelField;
import com.bray.util.DateUtil;

/**
 * @author:wuzhiyuan
 * @description: 用户统计模板
 * @date: Created in 16:41 2018/4/12
 * @modified By:
 */
public class UserDataStatisticsModule {

    // 时间
    @ExcelField(title = "时间", order = 1)
    private String time;
    // 下载用户数
    @ExcelField(title = "下载用户数", order = 2)
    private Integer loadUserCount;
    // 注册用户数
    @ExcelField(title = "注册用户数", order = 3)
    private Integer registerUserCount;
    // 填写资料用户数
    @ExcelField(title = "填写资料用户数", order = 4)
    private Integer fillDataCount;
    // 申请实名用户数
    @ExcelField(title = "申请实名用户数", order = 5)
    private Integer applyRealnameCount;
    // 注册下载比
    @ExcelField(title = "申请实名用户数", order = 6)
    private String registerLoadPer;
    // 填写资料用户比
    @ExcelField(title = "填写资料用户比", order = 7)
    private String fillDataUserPer;
    // 实名认证提交比
    @ExcelField(title = "实名认证提交比", order = 8)
    private String realnameSubmitPer;
    // 激活设备数
    @ExcelField(title = "激活设备数", order = 9)
    private Integer activeDeviceNumber;

    public UserDataStatisticsModule(UserDataStatistics userDataStat) {
        this.time = DateUtil.formatDate(userDataStat.getTime(), DateUtil.PATTERN_yyyyMMdd2);
        this.loadUserCount = userDataStat.getLoadUserCount();
        this.registerUserCount = userDataStat.getRegisterUserCount();
        this.fillDataCount = userDataStat.getFillDataCount();
        this.applyRealnameCount = userDataStat.getApplyRealnameCount();
        this.registerLoadPer = userDataStat.getRegisterLoadPer();
        this.fillDataUserPer = userDataStat.getFillDataUserPer();
        this.realnameSubmitPer = userDataStat.getRealnameSubmitPer();
        this.activeDeviceNumber = userDataStat.getActiveDeviceNumber();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getLoadUserCount() {
        return loadUserCount;
    }

    public void setLoadUserCount(Integer loadUserCount) {
        this.loadUserCount = loadUserCount;
    }

    public Integer getRegisterUserCount() {
        return registerUserCount;
    }

    public void setRegisterUserCount(Integer registerUserCount) {
        this.registerUserCount = registerUserCount;
    }

    public Integer getFillDataCount() {
        return fillDataCount;
    }

    public void setFillDataCount(Integer fillDataCount) {
        this.fillDataCount = fillDataCount;
    }

    public Integer getApplyRealnameCount() {
        return applyRealnameCount;
    }

    public void setApplyRealnameCount(Integer applyRealnameCount) {
        this.applyRealnameCount = applyRealnameCount;
    }

    public String getRegisterLoadPer() {
        return registerLoadPer;
    }

    public void setRegisterLoadPer(String registerLoadPer) {
        this.registerLoadPer = registerLoadPer;
    }

    public String getFillDataUserPer() {
        return fillDataUserPer;
    }

    public void setFillDataUserPer(String fillDataUserPer) {
        this.fillDataUserPer = fillDataUserPer;
    }

    public String getRealnameSubmitPer() {
        return realnameSubmitPer;
    }

    public void setRealnameSubmitPer(String realnameSubmitPer) {
        this.realnameSubmitPer = realnameSubmitPer;
    }

    public Integer getActiveDeviceNumber() {
        return activeDeviceNumber;
    }

    public void setActiveDeviceNumber(Integer activeDeviceNumber) {
        this.activeDeviceNumber = activeDeviceNumber;
    }
}
