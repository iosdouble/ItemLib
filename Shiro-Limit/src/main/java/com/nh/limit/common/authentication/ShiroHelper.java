package com.nh.limit.common.authentication;


import com.nh.limit.common.annotation.Helper;
import org.apache.shiro.authz.AuthorizationInfo;

/**
 * @Classname ShiroHelper
 * @Description TODO
 * @Date 2019/10/17 10:13 AM
 * @Created by nihui
 */
@Helper
public class ShiroHelper extends ShiroRealm {

    /**
     * 获取当前用户的角色和权限集合
     *
     * @return AuthorizationInfo
     */
    public AuthorizationInfo getCurrentuserAuthorizationInfo() {
        return super.doGetAuthorizationInfo(null);
    }

}
