package com.bray.web.controller;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 11:53 2019/1/11
 * @Modified By:
 */

import com.bray.aop.cache.RedisPoolCache;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.WySafedomain;
import com.bray.model.enums.DomainType;
import com.bray.service.impl.DomainWebServiceImpl;
import com.bray.util.TStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Objects;

@RequestMapping("/domain")
@Controller
@Slf4j
public class WechatDomainController {
    @Resource
    private RedisPoolCache redisObj;
    @Resource
    private DomainWebServiceImpl domainWebService;
    /**
     *  获取安全域名
     * @return
     */
    @RequestMapping("/safe-domain")
    @ResponseBody
    public RestResponseBo  safeDomain() {
        String safeDomain = "";
        Object safeDomains = redisObj.getRedisValueByKey("safeDomains");
        if(!Objects.isNull(safeDomains)) {
            return new RestResponseBo(true,TStringUtil.getDomainOfOne(String.valueOf(safeDomains)));
        }
        WySafedomain safeDomainObj = domainWebService.getSafeDomain();
        safeDomain = TStringUtil.getDomainOfOne(safeDomainObj.getSafeUrl());
        redisObj.saveDataToRedis("safeDomains",safeDomainObj.getSafeUrl());
        return new RestResponseBo(true,safeDomain);
    }
}
