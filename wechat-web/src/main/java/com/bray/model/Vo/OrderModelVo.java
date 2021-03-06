package com.bray.model.Vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author:wuzhiyuan
 * @Description: 订单实体类
 * @Date: Created in 10:33 2018/9/11
 * @Modified By:
 */
@Data
public class OrderModelVo {
    /**
     * 总金额
     */
    private String total;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 商品名称
     */
    private String title;
    /**
     * 商品名称
     */
    private String size;
    /**
     * 具体地址
     */
    private String address;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 订购数量
     */
    private int num;
    /**
     * 订单类型
     */
    private int nid;
    /**
     * 县级
     */
    private String county;
    /**
     * 库存数量
     */
    private int number;
    /**
     * 留言
     */
    private String message;
    /**
     * 时间
     */
    private Date orderDate;
    private String ip;
    private String sid;
    private int age;
    private String height;
    private String weight;
    private String idnumber;
    private String tel;
    private String code;
    private String qq;
    private String wechat;
    private String email;
}