package com.nh.limit.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nh.limit.system.entity.UserRole;

import java.util.List;

/**
 * @Classname IUserRoleService
 * @Description TODO
 * @Date 2019/10/12 10:29 AM
 * @Created by nihui
 */
public interface IUserRoleService extends IService<UserRole> {
    /**
     * 通过角色 id 删除
     *
     * @param roleIds 角色 id
     */
    void deleteUserRolesByRoleId(List<String> roleIds);

    /**
     * 通过用户 id 删除
     *
     * @param userIds 用户 id
     */
    void deleteUserRolesByUserId(List<String> userIds);
}
