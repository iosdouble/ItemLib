package com.nh.limit.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Classname Helper
 * @Description TODO
 * @Date 2019/10/17 10:15 AM
 * @Created by nihui
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Helper {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
