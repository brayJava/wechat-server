package com.bray.util;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 11:06 2019/1/11
 * @Modified By:
 */
public class TStringUtil {

    /**
     * 处理非分享域名，将其多个用逗号隔开存入库中
     * @param str 非分享域名
     * @return
     */
    public static String dealStr(String str) {
        if(StringUtils.isEmpty(str)) return "";
        String replaceUrlStr = str.trim().replace(" ", "");
        String[] noShareDomains = replaceUrlStr.split("\n");
        if(Objects.isNull(noShareDomains) || noShareDomains.length == 0) return "";
        String tstr = Arrays.stream(noShareDomains).map(s -> s.trim()).collect(Collectors.joining(","));
        return tstr;
    }

    public static String getDomainOfOne(String safeDomains) {
        String wySub  = "";
        String[] wySubs = String.valueOf(safeDomains).split(",");
        int v = (int)Math.floor(Math.random() * wySubs.length); //在域名中求整数
        wySub = wySubs[v];
        return wySub;
    }
}
