package com.bray.model.Vo;

import lombok.Data;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 1:20 2019/2/24
 * @Modified By:
 */
@Data
public class OrderDetailVo {
    /**
     * 订单内容展示
     */
    private String showHtml;
    /**
     * 金额
     */
    private double money;
    /**
     * 订单数量
     */
    private int count;

}
