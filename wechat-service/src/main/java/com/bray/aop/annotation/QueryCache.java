package com.bray.aop.annotation;

import java.lang.annotation.*;

/**
 * @Author:wuzhiyuan
 * @Description: 元注解QueryCache 用来标识查询数据库的方法
 * @Date: Created in 14:14 2018/8/14
 * @Modified By:
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryCache {
    /**
     * 人脉类型
     * @return
     */
    public String relationType() default "";

    public String serviceType() default "";
}
