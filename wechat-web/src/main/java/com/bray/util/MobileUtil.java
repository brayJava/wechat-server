package com.bray.util;

import com.bray.aop.cache.RedisPoolCache;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
@Slf4j
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

    /**
     * 分析访问来源
     * @param request
     * @param redisObj
     */
    public static void analysisMobileFrom(HttpServletRequest request, RedisPoolCache redisObj,int userId) {
        String ipAddr = MobileUtil.getIpAddr(request);
        log.info("访问真实ip为:"+ipAddr);
        String ipdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_yyyyMMddHH));

        //存储ip值(set存储，保证唯一)
        if(userId != 1)
            redisObj.setRedis("request-ip-"+userId,ipdate+ipAddr);
        redisObj.setRedis("request-ip",ipdate+ipAddr);
        // String nowtime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_yyyy_MM_dd_HH_mm_ss));
        // String userAgentWX = request.getHeader("user-agent").toLowerCase();
        // int startIndex = userAgentWX.indexOf("(");
        // int endIndex = userAgentWX.indexOf(")");
        // String xinhao = userAgentWX.substring(startIndex+1, endIndex);
        // if(userAgentWX.contains("iphone") || userAgentWX.contains("ipad")) {
        //     redisObj.lpushRedis("fromIphone_"+userId,nowtime+": "+xinhao+"ip["+ipAddr+"]");
        //     redisObj.lpushRedis("fromIphone",nowtime+": "+xinhao+"ip["+ipAddr+"]");
        // }
        // else if(userAgentWX.contains("android")) {
        //     String linuxgo = xinhao.substring(xinhao.indexOf("linux;") + 6, xinhao.indexOf("build"));
        //     redisObj.lpushRedis("fromAndroid_"+userId,nowtime+": "+linuxgo+"ip["+ipAddr+"]");
        //     redisObj.lpushRedis("fromAndroid",nowtime+": "+linuxgo+"ip["+ipAddr+"]");
        // } else {
        //     redisObj.lpushRedis("fromOther_"+userId,nowtime+": "+xinhao+"ip["+ipAddr+"]");
        //     redisObj.lpushRedis("fromOther",nowtime+": "+xinhao+"ip["+ipAddr+"]");
        // }
    }

    /**
     * 获取当前网络ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
