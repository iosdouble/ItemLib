package com.nh.limit.common.authentication;

import com.nh.limit.system.entity.Role;
import com.nh.limit.system.entity.User;
import com.nh.limit.system.service.IMenuService;
import com.nh.limit.system.service.IRoleService;
import com.nh.limit.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Classname ShiroRealm
 * @Description TODO
 * @Date 2019/10/12 10:01 AM
 * @Created by nihui
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;


    /**
     * 授权模块，获取用户角色和权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUsername();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //获取用户角色集合
        List<Role> roleList = this.roleService.findUserRole(userName);
        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);


        //获取用户权限集合


        return null;
    }

    /**
     * 用户认证
     *
     * @param token AuthenticationToken 身份认证token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户输入的用户名以及密码
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        //通过用户名到数据库中查询用户信息
        User user  = this.userService.findByName(userName);

        if (user == null){
            throw new UnknownAccountException("账号未注册");
        }
        if (!StringUtils.equals(password,user.getPassword())){
            throw new IncorrectCredentialsException("用户名或密码错误");
        }
        if (User.STATUS_LOCK.equals(user.getStatus())){
            throw new LockedAccountException("账号被锁定，请联系管理员");
        }

        return new SimpleAuthenticationInfo(user,password,getName());
    }

    /**
     * 清除当前用户权限缓存
     * 使用方式：在需要清除用户权限的地方注入 ShiroRealm，然后调用clearCache方法
     */
    public void clearCache(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
