package com.bray.quartz;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.dto.EffectiveType;
import com.bray.mapper.WySafedomainMapper;
import com.bray.mapper.WyUserMapper;
import com.bray.model.WySafedomain;
import com.bray.model.WyUser;
import com.bray.model.WyUserExample;
import com.bray.util.DateUtil;
import com.bray.util.HttpUtil;
import com.bray.wechat.WechatTemplateMessageServcie;
import com.bray.wechat.bean.OrderTemplateKeyParam;
import com.bray.wechat.bean.WechatConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Author:wuzhiyuan
 * @Description: redis 同步
 * @Date: Created in 0:23 2019/1/30
 * @Modified By:
 */
@Service
@Slf4j
public class RedisSynchronousTask {

    @Resource
    private RedisPoolCache redisObj;

    @Resource
    private WyUserMapper wyUserMapper;

    @Resource
    private WechatTemplateMessageServcie wechatTemplateMessageServcie;

    @Resource
    private WySafedomainMapper wySafedomainMapper;

    @Scheduled(cron = "0 */1 * * * ?")
    public void recordMinRedis() {
        String nowtime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_yyyy_MM_dd_HH_mm_ss));
        // String nowday = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_yyyy_MM_dd));
        Set<String> newipnums = redisObj.smembersRedis("request-ip");
        Object ipobj = redisObj.getRedisValueByKey("ipnums");
        if(!Objects.isNull(ipobj)) {
            Integer ipnum = Integer.valueOf(String.valueOf(ipobj));
            int minipCount = newipnums.size() - ipnum;
            redisObj.saveDataToRedis("minipCount",minipCount);
            log.info("每分钟ip量为：" + minipCount);
            redisObj.lpushRedis("minipCountList",nowtime+" --> "+minipCount);
            // String maxIpNum = redisObj.getMapRedis("maxIpNum", nowday);
            // if(StringUtils.isEmpty(maxIpNum) || Integer.valueOf(maxIpNum.split("#")[1]) < minipCount)
            //     redisObj.setMapRedis("maxIpNum",nowday,nowtime+"#"+minipCount);
        }
        redisObj.saveDataToRedis("ipnums",newipnums.size());
        //每个用户ip统计
        WyUserExample wyUserExample = new WyUserExample();
        wyUserExample.createCriteria().andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
        List<WyUser> wyUsers = wyUserMapper.selectByExample(wyUserExample);
        for (WyUser wyUser:wyUsers) {
            Set<String> user_newipnums = redisObj.smembersRedis("request-ip-"+wyUser.getId());
            Object user_ipobj = redisObj.getRedisValueByKey("ipnums-"+wyUser.getId());
            if(!Objects.isNull(user_ipobj)) {
                Integer user_ipnum = Integer.valueOf(String.valueOf(user_ipobj));
                int user_minipCount = user_newipnums.size() - user_ipnum;
                redisObj.saveDataToRedis("minipCount-"+wyUser.getId(),user_minipCount);
                log.info("用户【"+wyUser.getUsername()+"】每分钟ip量为：" + user_minipCount);
                redisObj.lpushRedis("minipCountList-"+wyUser.getId(),nowtime+" --> "+user_minipCount);
            }
            redisObj.saveDataToRedis("ipnums-"+wyUser.getId(),user_newipnums.size());
        }
    }

    /**
     * 检查域名
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void checkDomain() {

        StringBuffer bfymBuffer = new StringBuffer();
        StringBuffer updateBuffer = new StringBuffer();
        String checkDomains = String.valueOf(redisObj.getRedisValueByKey("checkDomains"));
        //查询所有安全域名
        WySafedomain wySafedomain = wySafedomainMapper.selectByPrimaryKey(1);
        String safeUrl = wySafedomain.getSafeUrl();
        String tempSafeUrl = safeUrl;
        if(!StringUtils.isEmpty(checkDomains)) {
            String[] domains = checkDomains.split(",");
            if(domains.length > 0) {
                for (int i=0 ; i< domains.length ; i++) {
                    String responseStr = HttpUtil.doGet("http://wx.rrbay.com/pro/wxUrlCheck2.ashx?key=aeb54b90be23c8839aeac4bd699569a2&url=" + domains[i]);
                    try {
                        Thread.sleep(2000);
                        log.info("正在检测域名----> "+domains[i]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(responseStr.contains("\"Code\":\"101\"") && !ymIsExist(domains[i])) {
                        if(safeUrl.contains(domains[i])) {
                            bfymBuffer.append(domains[i]+"(订单域名被封)"+",");
                            tempSafeUrl = tempSafeUrl.replace(domains[i],"");
                        } else {
                            bfymBuffer.append(domains[i]+",");
                        }
                        redisObj.lpushRedis("bfymList",domains[i]);
                    }
                }
                //判断安全域名是否被封，封了则删除数据库中域名
                if(!safeUrl.equals(tempSafeUrl)) {
                    String[] tempSafeUrls = tempSafeUrl.split(",");
                    for (String s: tempSafeUrls) {
                        if(!StringUtils.isEmpty(s))
                            updateBuffer.append(s+",");
                    }
                    if(!StringUtils.isEmpty(updateBuffer.toString())) {
                        wySafedomain.setSafeUrl(updateBuffer.toString().substring(0,updateBuffer.lastIndexOf(",")));
                        try {
                            wySafedomainMapper.updateByPrimaryKeySelective(wySafedomain);
                            redisObj.deleteDataOfRedis("safeDomains");
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("安全域名更新失败。。。");
                        }
                    } else {
                        OrderTemplateKeyParam orderTemplateKeyParam = new OrderTemplateKeyParam();
                        orderTemplateKeyParam.setFirst("已经没有可用的下单域名，请及时处理！！");
                        orderTemplateKeyParam.setKeyword1("参考意见：可用临时域名代替");
                        orderTemplateKeyParam.setKeyword2(DateUtil.formatDate(new Date(), DateUtil.PATTERN_yyyy_MM_dd_HH_mm));
                        orderTemplateKeyParam.setRemark("以上警告只发送一次...");
                        try {
                            sendWechatMessage(wySafedomain, orderTemplateKeyParam);
                            wySafedomain.setSafeUrl("");
                            wySafedomainMapper.updateByPrimaryKeySelective(wySafedomain);
                            redisObj.deleteDataOfRedis("safeDomains");
                        } catch (Exception e) {
                            log.error("---------微信下行通知发送错误");
                            e.printStackTrace();
                        }
                    }
                }
            }
            if(!StringUtils.isEmpty(bfymBuffer.toString())) {
                OrderTemplateKeyParam orderTemplateKeyParam = new OrderTemplateKeyParam();
                orderTemplateKeyParam.setFirst("有域名已经屏蔽，请及时处理！！");
                orderTemplateKeyParam.setKeyword1("屏蔽域名 【"+bfymBuffer.toString().substring(0,bfymBuffer.lastIndexOf(","))+"】");
                orderTemplateKeyParam.setKeyword2(DateUtil.formatDate(new Date(), DateUtil.PATTERN_yyyy_MM_dd_HH_mm));
                orderTemplateKeyParam.setRemark("以上域名只通知一次...");
                try {
                    //查询所有openid
                    sendWechatMessage(wySafedomain, orderTemplateKeyParam);
                } catch (Exception e) {
                    log.error("---------微信下行通知发送错误");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 下行通知
     * @param wySafedomain
     * @param orderTemplateKeyParam
     * @throws InterruptedException
     */
    private void sendWechatMessage(WySafedomain wySafedomain, OrderTemplateKeyParam orderTemplateKeyParam) throws InterruptedException {
        //查询所有openid
        if(StringUtils.isEmpty(wySafedomain.getMail())){
            wySafedomain.setMail(WechatConstant.DEFULAT_TOUSER);
        }
        String[] wechats = wySafedomain.getMail().split(",");
        for(String openid: wechats) {
            Thread.sleep(1000);
            //发送微信下行通知
            wechatTemplateMessageServcie.sendWechatMsg(openid,orderTemplateKeyParam);
        }
    }

    //判断被封域名在redis中是否存在
    private boolean ymIsExist(String domain) {
        List<String> list = redisObj.lrangeRedis("bfymList", 0, 10000000);
        boolean contains = list.contains(domain);
        return  contains;
    }

}
