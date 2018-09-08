package com.bray.aop;

import com.bray.aop.annotation.QueryCache;
import com.bray.aop.cache.RedisCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.Clock;
import java.util.Objects;

/**
 * @Author:wuzhiyuan
 * @Description: AOP实现Redis缓存处理
 * @Date: Created in 14:32 2018/8/14
 * @Modified By:
 */
@Component
@Aspect
public class RedisAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisCache redisCache;
    /**
     * 定义切点，拦截所有元注解Querycache注解的方法
     */
    @Pointcut("@annotation(com.bray.aop.annotation.QueryCache)")
    public void pointcutMethod() {
    }
    /**
     * 环绕处理，先从Redis里获取缓存,查询不到，就查询MySQL数据库，
     * 然后再保存到Redis缓存里
     * @param proceedingJoinPoint 切面参数
     */
    @Around(value = "pointcutMethod()&& @annotation(queryCache)")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint, QueryCache queryCache) {
        long startTime = Clock.systemDefaultZone().millis();
        //开始从redis中读取数据
        String redisKey = getRedisKey(proceedingJoinPoint,queryCache);
        Object obj = redisCache.getRedisValueByKey(redisKey);
        if(!Objects.isNull(obj)) {
            logger.info(">>>>>>>>>>>>>>>>开始从redis读取数据>>>>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>>>>>>Redis的KEY值为:{}",redisKey);
            logger.info(">>>>>>>>>>>>>>>>Redis的VALUE值为:{}",obj.toString());
            logger.info(">>>>>>>>>>>>>>>>从redis读取数据完毕>>>>>>>>>>>>>>");
            long endTime = Clock.systemDefaultZone().millis();
            logger.info(">>>>>>>>>>>>>>>>从redis读取数据所耗时 {}ms",endTime-startTime);
            return obj;
        }
        //如果redis中没有对于的数据，则调用方法原有逻辑，查询数据库
        try {
            long sTime = Clock.systemDefaultZone().millis();
            obj = proceedingJoinPoint.proceed();
            long mysqlTime = Clock.systemDefaultZone().millis();
            logger.info(">>>>>>>>>>>>>>>>从mysql读取数据所耗时 {}ms",mysqlTime-sTime);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (Objects.isNull(obj)) return null;
        //将数据库查询的数据放入redis中
        String code = redisCache.saveDataToRedis(redisKey, obj);
        if(code.equals("OK")){
            logger.info(">>>>>>>>>>>>>>>>开始从mysql读取数据>>>>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>>>>>>数据成功保存到Redis缓存!!!>>>>>>>>>>>>>>>>");
            logger.info("Redis的KEY值:"+redisKey);
            logger.info("Redis的VALUE值:"+obj.toString());
            long endMysqlTime = Clock.systemDefaultZone().millis();
            logger.info(">>>>>>>>>>>>>>>>将数据存入redis耗时 {}ms",endMysqlTime-startTime);
        }

        return obj;
    }
    /**
     * 获取rediskey，格式为{查询用户：articleId}
     * @param proceedingJoinPoint
     */
    private String getRedisKey(ProceedingJoinPoint proceedingJoinPoint,QueryCache queryCache) {
        Object[] args = proceedingJoinPoint.getArgs();
        String fieldValue = null;
        try {
            fieldValue = String.valueOf(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String serviceType = queryCache.serviceType();
        String redisKey = new StringBuilder().append(serviceType).append(":")
                .append(fieldValue).toString();
        return redisKey;
    }
}
