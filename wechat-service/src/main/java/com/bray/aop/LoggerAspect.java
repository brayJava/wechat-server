package com.bray.aop;

import com.bray.aop.annotation.LoggerManage;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Clock;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 11:10 2018/8/24
 * @Modified By:
 */
@Slf4j
@Aspect
@Component
public class LoggerAspect {

    private long startTime = 0L;

    private long endTime = 0L;

    private StringBuilder methodBuf = new StringBuilder();
    /**
     * 定义日志aop元注解切点
     */
    @Pointcut("@annotation(com.bray.aop.annotation.LoggerManage)")
    public void logPointcut() {}

    @Before("logPointcut() && @annotation(loggerManage)")
//    @Before("within(com.whiteplanet.scheduler.task.SchedulerTime..*) && @annotation(loggerManage)")
    public void beforeService(JoinPoint joinPoint, LoggerManage loggerManage) {
        startTime = Clock.systemDefaultZone().millis();
        methodBuf.append("\n");
        methodBuf.append("-------执行："+loggerManage.description()+"开始------ \n");
    }
    @AfterReturning("logPointcut() && @annotation(loggerManage)")
//    @AfterReturning("within(com.whiteplanet.scheduler.task.SchedulerTime..*) && @annotation(loggerManage)")
    public void endService(JoinPoint joinPoint, LoggerManage loggerManage) {
        endTime = Clock.systemDefaultZone().millis();
        methodBuf.append("-------共消耗: "+(endTime-startTime)/1000+"s------ \n");
        methodBuf.append("-------方法: "+joinPoint.getSignature().getName()+"执行结束------");
        log.info("{}",methodBuf.toString());
    }
//    @Around("logPointcut() && @annotation(loggerManage)")
//    public void AroundService(ProceedingJoinPoint joinPoint, LoggerManage loggerManage) {
//        startTime = Clock.systemDefaultZone().millis();
//        methodBuf.append("\n");
//        methodBuf.append("-------执行："+loggerManage.description()+"开始------ \n");
//        try {
//            joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        endTime = Clock.systemDefaultZone().millis();
//        methodBuf.append("-------共消耗: "+(endTime-startTime)/1000+"s------ \n");
//        methodBuf.append("-------方法: "+joinPoint.getSignature().getName()+"执行结束------");
//        log.info("{}",methodBuf.toString());
//    }
}
