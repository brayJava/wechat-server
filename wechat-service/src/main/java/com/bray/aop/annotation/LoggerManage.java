package com.bray.aop.annotation;

import java.lang.annotation.*;

/**
 * @description: 日志注解
 * @author wuzhiyuan
 * @date 2017年12月7日  上午11:34:57
 * @version 1.0
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {

	public String description();

	public String serviceType() default "";
}
