package com.bray.service;

import com.bray.mapper.WyOrderLogMapper;
import com.bray.mapper.WyOrderMapper;
import com.bray.model.Vo.OrderModelVo;
import com.bray.model.WyOrder;
import com.bray.model.WyOrderLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author:wuzhiyuan
 * @Description: 订单详情
 * @Date: Created in 16:24 2018/9/11
 * @Modified By:
 */
@Service
@Slf4j
public class OrderWebServiceImpl implements IOrderWebService {
    @Resource
    private WyOrderMapper wyOrderMapper;

    @Resource
    private WyOrderLogMapper wyOrderLogMapper;
    /**
     * 订单记录插入
     * @param orderModelVo
     */
    @Override
    public void insert(OrderModelVo orderModelVo) {
        WyOrder wyOrder = new WyOrder();
        wyOrder.setName(orderModelVo.getName());
        wyOrder.setPhone(orderModelVo.getPhone());
        wyOrder.setAge(orderModelVo.getAge());
        wyOrder.setPrice(Double.valueOf(orderModelVo.getTotal()));
        wyOrder.setAddress(orderModelVo.getAddress());
        wyOrder.setCity(orderModelVo.getCity());
        wyOrder.setCounty(orderModelVo.getCounty());
        wyOrder.setProvince(orderModelVo.getProvince());
        wyOrder.setMessage(orderModelVo.getMessage());
        wyOrder.setEmail(orderModelVo.getEmail());
        wyOrder.setIdnumber(orderModelVo.getIdnumber());
        wyOrder.setQq(orderModelVo.getQq());
        wyOrder.setTitle(orderModelVo.getTitle());
        wyOrder.setSize(orderModelVo.getSize());
        wyOrder.setWechat(orderModelVo.getWechat());
        wyOrder.setNum(orderModelVo.getNum());
        wyOrder.setNumber(orderModelVo.getNumber());
        wyOrder.setCreateTime(new Date());
        wyOrder.setUpdateTime(new Date());
        try {
            wyOrderMapper.insertSelective(wyOrder);
        } catch (RuntimeException e) {
            log.error("------------订单信息插入异常");
            e.printStackTrace();
        }

    }

    /**
     * 下单日志相关
     * @param type
     */
    @Override
    public void insertOrderLog(int type) {

        WyOrderLog wyOrderLog = new WyOrderLog();
        wyOrderLog.setType(type);
        wyOrderLog.setCreateTime(new Date());
        wyOrderLog.setUpdateTime(new Date());
        try {
            wyOrderLogMapper.insertSelective(wyOrderLog);
        } catch (Exception e) {
            log.error("------------订单日志信息插入异常");
            e.printStackTrace();
        }

    }
}
