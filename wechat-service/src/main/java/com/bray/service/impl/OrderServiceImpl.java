package com.bray.service.impl;

import com.bray.dto.EffectiveType;
import com.bray.excel.modules.XFOrderModule;
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
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<WyOrder> queryAllOrder(String startTime, String endTime) {
        WyOrderExample wyOrderExample = new WyOrderExample();
        wyOrderExample.createCriteria()
                .andUpdateTimeBetween(DateUtil.parseDate(startTime,DateUtil.PATTERN_yyyy_MM_dd_HH_mm)
                        ,DateUtil.parseDate(endTime,DateUtil.PATTERN_yyyy_MM_dd_HH_mm))
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
        String startDate = DateUtil.getStartDate(searchModelVo.getStart());
        String lastDate = DateUtil.getLastDate(searchModelVo.getEnd());
        criteria.andCreateTimeBetween(
                DateUtil.parseDate(startDate,DateUtil.PATTERN_yyyy_MM_dd_HH_mm),
                DateUtil.parseDate(lastDate,DateUtil.PATTERN_yyyy_MM_dd_HH_mm))
                .andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
        if(!StringUtils.isEmpty(searchModelVo.getUsername()) && !StringUtils.isEmpty(searchModelVo.getPhone())) {
            criteria.andNameLike(searchModelVo.getUsername()).andPhoneEqualTo(searchModelVo.getPhone());
        }
        if(!StringUtils.isEmpty(searchModelVo.getUsername())) {
            criteria.andNameLike(searchModelVo.getUsername());
        }
        if(!StringUtils.isEmpty(searchModelVo.getPhone())) {
            criteria.andPhoneEqualTo(searchModelVo.getPhone());
        }
        wyOrderExample.setOrderByClause("update_time desc");
        List<WyOrder> wyOrders = wyOrderMapper.selectByExample(wyOrderExample);
        return wyOrders;
    }
    /**
     * excel列表
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<XFOrderModule> excelList(String startTime,String endTime) {
        String startDate = DateUtil.getStartDate(startTime);
        String lastDate = DateUtil.getLastDate(endTime);
        List<XFOrderModule> xfOrderModules = wyOrderMapper.selectOfOrders(startDate,lastDate);
        return xfOrderModules;

    }

}
