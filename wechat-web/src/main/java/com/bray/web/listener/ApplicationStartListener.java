package com.bray.web.listener;
import com.bray.config.WebConst;
import com.bray.dto.ConstatFinal;
import com.bray.model.Bo.PrimarySubDomain;
import com.bray.model.enums.DomainType;
import com.bray.service.impl.DomainServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * @Author:wuzhiyuan
 * @Description:  容器启动监听
 * @Date: Created in 10:54 2018/8/28
 * @Modified By:
 */
@Component
@Slf4j
public class ApplicationStartListener
        implements ApplicationListener<ContextRefreshedEvent>{
//    @Resource
//    private DomainServiceImpl domainService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        //获取所有有效域名域名
//        List<PrimarySubDomain> allEffectiveDomain =
//                (List<PrimarySubDomain>) domainService.queryAllEffectiveDomain(ConstatFinal.QUERY_WEB);
//        if(!CollectionUtils.isEmpty(allEffectiveDomain)) {
//            allEffectiveDomain.forEach(primarySubDomain -> {
//                String type = primarySubDomain.getWyDomain().getType();
//                //盘判断是否是分享主域名
//                if(DomainType.SHAREDOMAIN.getType().equals(type)) {
//                    //将可分享主域名的子域名都添加到缓存中
//                    WebConst.domainMap.put(WebConst.SUB_SHARE_DOMAIN,primarySubDomain.getWySubdomainList());
//                }else if(DomainType.COMMONDOMAIN.getType().equals(type)) {
//                    //将内容相关的子域名放入缓存中
//                    WebConst.domainMap.put(WebConst.SUB_COMMON_DOMAIN,primarySubDomain.getWySubdomainList());
//                }
//            });
//        }
//        //将所有有效域名方法缓存中
//        WebConst.domainMap.put(WebConst.ALL_DOMAIN,allEffectiveDomain);
        log.info("【----------------------系统启动完毕------------------】");
    }
}