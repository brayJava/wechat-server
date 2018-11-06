package com.bray.service.impl;

import com.bray.mapper.WyOrderLogMapper;
import com.bray.model.WyOrderLog;
import com.bray.model.WyOrderLogExample;
import com.bray.service.IOrderAdminService;
import com.bray.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 订单日志服务
 * @Date: Created in 16:35 2018/11/6
 * @Modified By:
 */
@Service
public class OrderAdminServiceImpl implements IOrderAdminService {

    @Resource
    private WyOrderLogMapper wyOrderLogMapper;
    /**
     * 获取最新订单数据
     * @return
     */
    @Override
    public List<WyOrderLog> queryOrderLogData() {

        List<WyOrderLog> wyOrderLogs = new ArrayList<>();
        try {
            WyOrderLogExample wyOrderLogExample = new WyOrderLogExample();
            wyOrderLogExample.createCriteria().andTypeEqualTo(2);
            wyOrderLogExample.setOrderByClause("create_time desc limit 20");
            wyOrderLogs = wyOrderLogMapper.selectByExample(wyOrderLogExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wyOrderLogs;
    }
}
