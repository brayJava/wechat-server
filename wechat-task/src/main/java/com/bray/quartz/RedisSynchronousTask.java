package com.bray.quartz;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.dto.EffectiveType;
import com.bray.mapper.WyUserMapper;
import com.bray.model.WyUser;
import com.bray.model.WyUserExample;
import com.bray.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

}
