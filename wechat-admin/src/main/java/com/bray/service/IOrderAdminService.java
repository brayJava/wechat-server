package com.bray.service;

import com.bray.model.WyOrderLog;

import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 订单相关服务
 * @Date: Created in 16:33 2018/11/6
 * @Modified By:
 */
public interface IOrderAdminService {

    List<WyOrderLog> queryOrderLogData();
}
