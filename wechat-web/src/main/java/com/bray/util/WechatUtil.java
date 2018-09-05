package com.bray.util;

import com.alibaba.fastjson.JSONObject;
import com.bray.config.WebConst;
import com.bray.dto.UrlConstant;
import com.bray.model.WySubdomain;
import org.springframework.util.CollectionUtils;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:wuzhiyuan
 * @Description: 微信业务相关工具方法
 * @Date: Created in 13:49 2018/9/5
 * @Modified By:
 */
public class WechatUtil {
    /**
     * 下一步跳转连接
     * @param domainType 域名类型
     * @param path       跳转相对路径
     * @return
     */
    public static JSONObject nextUrlBuild(String domainType,String path) {
        JSONObject jsonObject = new JSONObject();
        long millis = Clock.systemDefaultZone().millis();
        //获取分享主域名
        List<WySubdomain> wySubdomains = (List<WySubdomain>) WebConst.domainMap.get(domainType);
        if(CollectionUtils.isEmpty(wySubdomains)) return jsonObject;
        String[] wySubs = wySubdomains.stream().map(wySubdomain -> wySubdomain.getSubDomain())
                .collect(Collectors.toList()).toArray(new String[wySubdomains.size()]);
        int v = (int)Math.random() * wySubs.length;
        String url = new StringBuilder().append(UrlConstant.HTTP_RUL).append(wySubs[v]).append(path).append(millis).toString();
        jsonObject.put("domain",wySubs[v]);
        jsonObject.put("url",url);
        return jsonObject;
    }
}
