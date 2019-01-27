package com.bray.util;

import com.bray.aop.cache.RedisPoolCache;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 23:19 2018/11/10
 * @Modified By:
 */
public class MobileUtil {

    // \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),
    // 字符串在编译时会被转码一次,所以是 "\\b"
    // \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)
    static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"
            +"|windows (phone|ce)|blackberry"
            +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
            +"|laystation portable)|nokia|fennec|htc[-_]"
            +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
    static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"
            +"|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

    //移动设备正则匹配：手机端、平板
    static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
    static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

    /**
     * 检测是否是移动设备访问
     *
     * @Title: check
     * @Date : 2014-7-7 下午01:29:07
     * @param userAgent 浏览器标识
     * @return true:移动设备接入，false:pc端接入
     */
    public static boolean check(String userAgent) {
        if (null == userAgent) {
            userAgent = "";
        }
        // 匹配
        Matcher matcherPhone = phonePat.matcher(userAgent);
        Matcher matcherTable = tablePat.matcher(userAgent);
        if (matcherPhone.find() || matcherTable.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static void analysisMobileFrom(HttpServletRequest request, RedisPoolCache redisObj) {
        String nowtime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_yyyy_MM_dd_HH_mm_ss));
        String userAgentWX = request.getHeader("user-agent").toLowerCase();
        int startIndex = userAgentWX.indexOf("(");
        int endIndex = userAgentWX.indexOf(")");
        String xinhao = userAgentWX.substring(startIndex+1, endIndex);
        if(userAgentWX.contains("iphone") || userAgentWX.contains("ipad")) {
            redisObj.lpushRedis("fromIphone",nowtime+":"+xinhao);
        }
        else if(userAgentWX.contains("android")) {
            redisObj.lpushRedis("fromAndroid",nowtime+":"+xinhao);
        } else {
            redisObj.lpushRedis("fromOther",nowtime+":"+xinhao);
        }
    }
}
