package com.bray.service;

import com.bray.mapper.WyOrderMapper;
import com.bray.model.Vo.OrderModelVo;
import com.bray.model.WyOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        wyOrder.setEmail(orderModelVo.getEmail());
        wyOrder.setIdnumber(orderModelVo.getIdnumber());
        wyOrder.setQq(orderModelVo.getQq());
        wyOrder.setTitle(orderModelVo.getTitle());
        wyOrder.setWechat(orderModelVo.getWechat());
        wyOrder.setNum(orderModelVo.getNum());
        wyOrder.setNumber(orderModelVo.getNumber());
        try {
            wyOrderMapper.insertSelective(wyOrder);
        } catch (Exception e) {
            log.error("------------订单信息插入异常");
            e.printStackTrace();
        }

    }
}
