package com.nh.limit.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname FebsUtil
 * @Description TODO
 * @Date 2019/10/12 10:17 AM
 * @Created by nihui
 */
@Slf4j
public class FebsUtil {

    /**
     * 正则校验
     *
     * @param regex 正则表达式字符串
     * @param value 要匹配的字符串
     * @return 正则校验结果
     */
    public static boolean match(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
