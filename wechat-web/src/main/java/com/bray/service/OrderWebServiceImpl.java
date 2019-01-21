package com.bray.service;

import com.bray.mapper.WyOrderCopyMapper;
import com.bray.mapper.WyOrderLogMapper;
import com.bray.mapper.WyOrderMapper;
import com.bray.model.Vo.OrderModelVo;
import com.bray.model.WyOrder;
import com.bray.model.WyOrderCopy;
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

    @Resource
    private WyOrderCopyMapper wyOrderCopyMapper;
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
        String size = orderModelVo.getSize();
        wyOrder.setSize(size);
        String sizenum = size.substring(0, size.indexOf("("));
        switch (orderModelVo.getNid()) {
            case 60 :
                wyOrder.setSize(sizenum + " 3");
                break;
            case 62 :
                wyOrder.setSize(sizenum + " 5");
                break;
            case 61 :
                wyOrder.setSize(sizenum + " 1");
                break;
        }
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
     * 订单记录插入
     * @param orderModelVo
     */
    @Override
    public void insertCopy(OrderModelVo orderModelVo) {
        WyOrderCopy wyOrderCopy= new WyOrderCopy();
        wyOrderCopy.setName(orderModelVo.getName());
        wyOrderCopy.setPhone(orderModelVo.getPhone());
        wyOrderCopy.setAge(orderModelVo.getAge());
        wyOrderCopy.setPrice(Double.valueOf(orderModelVo.getTotal()));
        wyOrderCopy.setAddress(orderModelVo.getAddress());
        wyOrderCopy.setCity(orderModelVo.getCity());
        wyOrderCopy.setCounty(orderModelVo.getCounty());
        wyOrderCopy.setProvince(orderModelVo.getProvince());
        wyOrderCopy.setMessage(orderModelVo.getMessage());
        wyOrderCopy.setEmail(orderModelVo.getEmail());
        wyOrderCopy.setIdnumber(orderModelVo.getIdnumber());
        wyOrderCopy.setQq(orderModelVo.getQq());
        wyOrderCopy.setTitle(orderModelVo.getTitle());
        wyOrderCopy.setSize(orderModelVo.getSize());
        wyOrderCopy.setWechat(orderModelVo.getWechat());
        wyOrderCopy.setNum(orderModelVo.getNum());
        wyOrderCopy.setNumber(orderModelVo.getNumber());
        wyOrderCopy.setCreateTime(new Date());
        wyOrderCopy.setUpdateTime(new Date());
        try {
            wyOrderCopyMapper.insertSelective(wyOrderCopy);
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
