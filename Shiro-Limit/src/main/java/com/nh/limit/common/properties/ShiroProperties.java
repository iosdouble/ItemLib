package com.nh.limit.common.properties;

import lombok.Data;

/**
 * @Classname ShiroProperties
 * @Description TODO
 * @Date 2019/10/11 3:55 PM
 * @Created by nihui
 */
@Data
public class ShiroProperties {

    //Session超时时间
    private long sessionTimeout;
    //Cookie超时时间
    private int cookieTimeout;
    //认证的url
    private String anonUrl;
    //登陆url
    private String loginUrl;
    //登陆成功
    private String successUrl;
    //登出
    private String logoutUrl;
    //未认证的url
    private String unauthorizedUrl;
}
