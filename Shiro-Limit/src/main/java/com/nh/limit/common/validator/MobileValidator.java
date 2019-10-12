package com.nh.limit.common.validator;

import com.nh.limit.common.annotation.IsMobile;
import com.nh.limit.common.entity.RegexpConstant;
import com.nh.limit.common.utils.FebsUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Classname MobileValidator
 * @Description TODO
 * @Date 2019/10/12 10:14 AM
 * @Created by nihui
 */
public class MobileValidator implements ConstraintValidator<IsMobile,String> {

    @Override
    public void initialize(IsMobile constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE_REG;
                return FebsUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
