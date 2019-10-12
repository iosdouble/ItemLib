package com.nh.limit.common.annotation;



import com.nh.limit.common.validator.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname IsMobile
 * @Description TODO
 * @Date 2019/10/12 10:12 AM
 * @Created by nihui
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileValidator.class)
public @interface IsMobile {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
