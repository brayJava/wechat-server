package com.bray.aop.annotation;

import java.lang.annotation.*;

/**
 * @Author:wuzhiyuan
 * @Description: 元注解 RedisCacheKey 是方法级别的注解，用来标注要查询数据的主键
 * @Date: Created in 14:18 2018/8/14
 * @Modified By:
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCacheKey {

}
