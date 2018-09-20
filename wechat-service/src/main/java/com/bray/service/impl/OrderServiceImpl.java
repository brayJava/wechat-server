package com.bray.service.impl;

import com.bray.mapper.WyOrderMapper;
import com.bray.model.WyOrder;
import com.bray.model.WyOrderExample;
import com.bray.service.IOrderService;
import com.bray.util.DateUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
/**
 * @Author:wuzhiyuan
 * @Description: 订单接口实现类
 * @Date: Created in 11:17 2018/9/19
 * @Modified By:
 */
@Service
public class OrderServiceImpl implements IOrderService<WyOrder>{

    @Resource
    private WyOrderMapper wyOrderMapper;
    /**
     * 查询订单时间
     * @param StartTime
     * @param endTime
     * @return
     */
    @Override
    public List<WyOrder> queryAllOrder(String StartTime, String endTime) {
        String startDate = DateUtil.getStartDate(StartTime);
        String lastDate = DateUtil.getLastDate(endTime);
        WyOrderExample wyOrderExample = new WyOrderExample();
        wyOrderExample.createCriteria()
                .andUpdateTimeBetween(DateUtil.parseDate(startDate,DateUtil.PATTERN_yyyy_MM_dd_HH_mm)
                        ,DateUtil.parseDate(lastDate,DateUtil.PATTERN_yyyy_MM_dd_HH_mm));
        wyOrderExample.setOrderByClause("update_time desc");
        List<WyOrder> wyOrders = wyOrderMapper.selectByExample(wyOrderExample);
        return wyOrders;
    }
}
