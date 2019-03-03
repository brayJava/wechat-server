package com.bray.quartz;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.dto.EffectiveType;
import com.bray.mapper.WyUserMapper;
import com.bray.model.WyUser;
import com.bray.model.WyUserExample;
import com.bray.util.DateUtil;
import com.bray.util.HttpUtil;
import com.bray.wechat.WechatTemplateMessageServcie;
import com.bray.wechat.bean.OrderTemplateKeyParam;
import com.bray.wechat.bean.WechatConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
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

    @Scheduled(cron = "0 */1 * * * ?")
    public void recordMinRedis() {
        String nowtime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_yyyy_MM_dd_HH_mm_ss));
        Set<String> newipnums = redisObj.smembersRedis("request-ip");
        Object ipobj = redisObj.getRedisValueByKey("ipnums");
        if(!Objects.isNull(ipobj)) {
            Integer ipnum = Integer.valueOf(String.valueOf(ipobj));
            int minipCount = newipnums.size() - ipnum;
            redisObj.saveDataToRedis("minipCount",minipCount);
            log.info("每分钟ip量为：" + minipCount);
            redisObj.lpushRedis("minipCountList",nowtime+" --> "+minipCount);
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
        String checkDomains = String.valueOf(redisObj.getRedisValueByKey("checkDomains"));
        if(!StringUtils.isEmpty(checkDomains)) {
            String[] domains = checkDomains.split(",");
            if(domains.length > 0) {
                for (int i=0 ; i< domains.length ; i++) {
                    String responseStr = HttpUtil.doGet("http://wx.rrbay.com/pro/wxUrlCheck2.ashx?key=aeb54b90be23c8839aeac4bd699569a2&url=" + domains[i]);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(responseStr.contains("\"Code\":\"101\"") && !ymIsExist(domains[i])) {
                        bfymBuffer.append(domains[i]+",");
                        redisObj.lpushRedis("bfymList",domains[i]);
                    }
                }
            }
            if(!StringUtils.isEmpty(bfymBuffer.toString())) {
                OrderTemplateKeyParam orderTemplateKeyParam = new OrderTemplateKeyParam();
                orderTemplateKeyParam.setFirst("有域名已经屏蔽，请及时处理！！");
                orderTemplateKeyParam.setKeyword1("屏蔽域名 【"+bfymBuffer.toString().substring(0,bfymBuffer.lastIndexOf(","))+"】");
                orderTemplateKeyParam.setKeyword2(DateUtil.formatDate(new Date(), DateUtil.PATTERN_yyyy_MM_dd_HH_mm));
                orderTemplateKeyParam.setRemark("以上域名只通知一次...");
                wechatTemplateMessageServcie.sendToUserTemplateMessage(WechatConstant.DEFULAT_TOUSER,orderTemplateKeyParam);
                wechatTemplateMessageServcie.sendToUserTemplateMessage("oE2ON5sFFHMjxk1OLQtpXwPgMxN0",orderTemplateKeyParam);
                wechatTemplateMessageServcie.sendToUserTemplateMessage("oE2ON5oxtNG_kjPDnjck2dD7OF24",orderTemplateKeyParam);
            }
        }
    }
    //判断被封域名在redis中是否存在
    private boolean ymIsExist(String domain) {
        List<String> list = redisObj.lrangeRedis("bfymList", 0, 10000000);
        boolean contains = list.contains(domain);
        return  contains;
    }

}
