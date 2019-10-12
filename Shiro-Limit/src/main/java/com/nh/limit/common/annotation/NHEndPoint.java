package com.nh.limit.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Classname NHEndPoint
 * @Description TODO
 * @Date 2019/10/11 3:45 PM
 * @Created by nihui
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface NHEndPoint {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
