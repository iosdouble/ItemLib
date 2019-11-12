package com.nh.limit.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Classname HttpContextUtil
 * @Description TODO
 * @Date 2019/10/30 4:05 PM
 * @Created by nihui
 */
public class HttpContextUtil {
    private HttpContextUtil(){

    }
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
