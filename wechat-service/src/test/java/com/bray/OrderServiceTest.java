package com.bray;

import com.alibaba.fastjson.JSONObject;
import com.bray.model.WyOrder;
import com.bray.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 订单相关测试
 * @Date: Created in 11:36 2018/9/19
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Resource
    private IOrderService iOrderService;

    @Test
    public void queryAllOrder() {
        List<WyOrder> wyOrders = (List<WyOrder>)iOrderService.queryAllOrder("2018-09-19", "2018-09-19");
        System.out.println(JSONObject.toJSONString(wyOrders));

    }
}
