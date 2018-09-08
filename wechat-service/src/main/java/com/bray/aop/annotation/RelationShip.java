package com.bray.aop.annotation;

import java.lang.annotation.*;

/**
 * @Author:wuzhiyuan
 * @Description: 人脉关系元注解
 * @Date: Created in 12:08 2018/8/24
 * @Modified By:
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RelationShip {
    /**
     * 人脉类型
     * @return
     */
    public String relationType() default "";

    public String serviceType() default "";
}
