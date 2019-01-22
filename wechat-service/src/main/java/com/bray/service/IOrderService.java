package com.bray.service;

import com.bray.excel.modules.XFOrderModule;
import com.bray.model.Vo.SearchModelVo;
import com.bray.model.WyOrder;

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
    /**
     * 删除订单
     * @param id
     */
    void delete(int id);
    /**
     * 根据条件查询
     * @param searchModelVo
     * @return
     */
    List<T> queryOrderByCondition(SearchModelVo searchModelVo);
    /**
     * excel列表
     * @param startTime
     * @param endTime
     * @return
     */
    List<XFOrderModule> excelList(String startTime, String endTime);


}
