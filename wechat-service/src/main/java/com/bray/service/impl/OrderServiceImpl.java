package com.bray.service.impl;

import com.bray.dto.EffectiveType;
import com.bray.mapper.WyOrderMapper;
import com.bray.model.Vo.SearchModelVo;
import com.bray.model.WyOrder;
import com.bray.model.WyOrderExample;
import com.bray.service.IOrderService;
import com.bray.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
/**
 * @Author:wuzhiyuan
 * @Description: 订单接口实现类
 * @Date: Created in 11:17 2018/9/19
 * @Modified By:
 */
@Service
@Slf4j
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
                        ,DateUtil.parseDate(lastDate,DateUtil.PATTERN_yyyy_MM_dd_HH_mm))
                            .andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
        wyOrderExample.setOrderByClause("update_time desc");
        List<WyOrder> wyOrders = wyOrderMapper.selectByExample(wyOrderExample);
        return wyOrders;
    }
    @Override
    public void delete(int id) {
        WyOrder wyOrder = wyOrderMapper.selectByPrimaryKey(id);
        wyOrder.setStatus(EffectiveType.EFFECTIVE_NO);
        try {
            wyOrderMapper.updateByPrimaryKeySelective(wyOrder);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新失败！！");
        }
    }
    /**
     * 根据条件查询订单
     */
    @Override
    public List<WyOrder> queryOrderByCondition(SearchModelVo searchModelVo) {

        WyOrderExample wyOrderExample = new WyOrderExample();
        WyOrderExample.Criteria criteria = wyOrderExample.createCriteria();
        criteria.andCreateTimeBetween(
                DateUtil.parseDate(searchModelVo.getStartTime(),DateUtil.PATTERN_yyyy_MM_dd_HH_mm),
                DateUtil.parseDate(searchModelVo.getEndTime(),DateUtil.PATTERN_yyyy_MM_dd_HH_mm));
        if(StringUtils.isEmpty(searchModelVo.getUsername()) && StringUtils.isEmpty(searchModelVo.getPhone())) {
            criteria.andNameLike(searchModelVo.getUsername()).andPhoneEqualTo(searchModelVo.getPhone());
        }
        if(StringUtils.isEmpty(searchModelVo.getUsername())) {
            criteria.andNameLike(searchModelVo.getUsername());
        }
        if(StringUtils.isEmpty(searchModelVo.getPhone())) {
            criteria.andPhoneEqualTo(searchModelVo.getPhone());
        }
        List<WyOrder> wyOrders = wyOrderMapper.selectByExample(wyOrderExample);
        return wyOrders;
    }
}
