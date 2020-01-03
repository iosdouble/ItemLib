package com.nh.limit.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname ControllerEndpoint
 * @Description TODO 控制器EndPoint
 * @Date 2019/10/11 3:30 PM
 * @Created by nihui
 */
//元注解用于标注在方法上
@Target(ElementType.METHOD)
//元注解用于标注作用时间
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerEndpoint {

    String operation() default "";
    String exceptionMessage() default "系统内部异常";
}
