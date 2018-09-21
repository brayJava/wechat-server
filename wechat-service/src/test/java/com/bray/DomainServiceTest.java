package com.bray;

import com.alibaba.fastjson.JSONObject;
import com.bray.dto.ConstatFinal;
import com.bray.service.impl.DomainServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author:wuzhiyuan
 * @Description: 域名查询相关测试
 * @Date: Created in 13:22 2018/8/31
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class DomainServiceTest {

    @Resource
    private DomainServiceImpl domainService;
    /**
     * 查询所有有效域名
     */
    @Test
    public void queryAllEffectiveDomain() {

        Object o = domainService.queryAllEffectiveDomain(ConstatFinal.QUERY_ADMIN,"");
        System.out.println(JSONObject.toJSON(o).toString());
    }
}