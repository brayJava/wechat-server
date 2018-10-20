package com.bray.service;

import com.bray.model.Vo.OrderModelVo;

/**
 * @Author:wuzhiyuan
 * @Description: 订单服务
 * @Date: Created in 13:04 2018/9/11
 * @Modified By:
 */
public interface IOrderWebService {

    void insert(OrderModelVo orderModelVo);
    /**
     *  订单相关日志
     * @param type
     */
    void insertOrderLog(int type);

}
