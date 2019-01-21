package com.bray.excel.modules;


import com.bray.excel.annotation.ExcelField;
import lombok.Data;

/**
 * @Author:wuzhiyuan
 * @Description: 顺丰单子模板
 * @Date: Created in 21:39 2019/1/20
 * @Modified By:
 */
@Data
public class XFOrderModule {
    /**
     * 用户订单号
     */
    @ExcelField(title = "用户订单号", order = 1)
    private String orderId;
    /**
     * 收件公司
     */
    @ExcelField(title = "收件公司", order = 2)
    private String consigneeCompany;
    /**
     * 收件人
     */
    @ExcelField(title = "收件人", order = 3)
    private String addressee;
    /**
     * 收件电话
     */
    @ExcelField(title = "收件电话", order = 4)
    private String phone;
    /**
     * 收件手机
     */
    @ExcelField(title = "收件手机", order = 5)
    private String mobilephone;
    /**
     * 收件详细地址
     */
    @ExcelField(title = "收件详细地址", order = 6)
    private String address;
    /**
     * 寄件物内容
     */
    @ExcelField(title = "寄件物内容", order = 7)
    private String content;
    /**
     * 寄件物数量
     */
    @ExcelField(title = "寄件物数量", order = 8)
    private int num;
    /**
     * 包裹重量
     */
    @ExcelField(title = "包裹重量", order = 9)
    private int bgweight;
    /**
     * 寄方备注
     */
    @ExcelField(title = "寄方备注", order = 10)
    private String jfbz;
    /**
     * 运费代付方式
     */
    @ExcelField(title = "运费代付方式", order = 11)
    private String yfdffs;
    /**
     * 业务类型
     */
    @ExcelField(title = "业务类型", order = 12)
    private String type;
    /**
     * 件数
     */
    @ExcelField(title = "件数", order = 13)
    private int jnum;
    /**
     * 代收金额
     */
    @ExcelField(title = "代收金额", order = 14)
    private int dsmoney;
    /**
     * 保价金额
     */
    @ExcelField(title = "保价金额", order = 15)
    private int bjmoney;
    /**
     * 标准化包装
     */
    @ExcelField(title = "标准化包装", order = 16)
    private String packingSpecification;
    /**
     * 个性化包装
     */
    @ExcelField(title = "个性化包装", order = 17)
    private String gxhSpecification;
    /**
     * 签回单
     */
    @ExcelField(title = "签回单", order = 18)
    private String qhd;
    /**
     * 自取件
     */
    @ExcelField(title = "自取件", order = 19)
    private String zqj;
    /**
     * 易碎性
     */
    @ExcelField(title = "易碎性", order = 20)
    private String ysx;
    /**
     * 电子签收
     */
    @ExcelField(title = "子签收", order = 21)
    private String dzqs;
    /**
     * 超长超重服务费
     */
    @ExcelField(title = "超长超重服务费", order = 22)
    private String serverfee;
    /**
     * 是否定时派送
     */
    @ExcelField(title = "是否定时派送", order = 23)
    private String sendintime;
    /**
     * 派送日期
     */
    @ExcelField(title = "派送日期", order = 24)
    private String psrq;
    /**
     * 派送时段
     */
    @ExcelField(title = "派送时段", order = 25)
    private String pssd;
    /**
     * 扩充字段
     */
    @ExcelField(title = "扩充字段", order = 26)
    private String kczd;


    @Override
    public String toString() {
        return "XFOrderModule{" +
                "orderId='" + orderId + '\'' +
                ", consigneeCompany='" + consigneeCompany + '\'' +
                ", addressee='" + addressee + '\'' +
                ", phone='" + phone + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", num=" + num +
                ", bgweight=" + bgweight +
                ", jfbz='" + jfbz + '\'' +
                ", yfdffs='" + yfdffs + '\'' +
                ", type='" + type + '\'' +
                ", jnum=" + jnum +
                ", dsmoney=" + dsmoney +
                ", bjmoney=" + bjmoney +
                ", packingSpecification='" + packingSpecification + '\'' +
                ", gxhSpecification='" + gxhSpecification + '\'' +
                ", qhd='" + qhd + '\'' +
                ", zqj='" + zqj + '\'' +
                ", ysx='" + ysx + '\'' +
                ", dzqs='" + dzqs + '\'' +
                ", serverfee='" + serverfee + '\'' +
                ", sendintime='" + sendintime + '\'' +
                ", psrq='" + psrq + '\'' +
                ", pssd='" + pssd + '\'' +
                ", kczd='" + kczd + '\'' +
                '}';
    }
}
