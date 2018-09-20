package com.bray.service;

import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 订单接口
 * @Date: Created in 10:47 2018/9/19
 * @Modified By:
 */
public interface IOrderService<T> {
    /**
     * 查询指定日期内订单数量，默认为当天
     * @param StartTime
     * @param endTime
     * @return
     */
    List<T> queryAllOrder(String StartTime,String endTime);
}
