package com.bray;
import com.bray.mapper.WyDomainMapper;
import com.bray.model.WyDomain;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 23:49 2018/8/26
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class DomainDaoTest {
    @Resource
    private WyDomainMapper wyDomainMapper;
    @Test
//    @Transactional
    public void insert () {
        WyDomain wyDomain = new WyDomain();
        wyDomain.setDomain("www.liming.cn");
        wyDomain.setDomainFrom("阿里云");
        wyDomain.setType("1");
        wyDomain.setStatus(1);
        wyDomain.setCreateTime(new Date());
        wyDomain.setUpdateTime(new Date());
        int insert = 0;
        try {
            insert = wyDomainMapper.insert(wyDomain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(insert);
    }
}
