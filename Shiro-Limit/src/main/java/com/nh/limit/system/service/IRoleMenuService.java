package com.nh.limit.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nh.limit.system.entity.RoleMenu;

import java.util.List;

/**
 * @Classname IRoleMenuService
 * @Description TODO
 * @Date 2019/10/12 10:29 AM
 * @Created by nihui
 */
public interface IRoleMenuService extends IService<RoleMenu> {
    /**
     * 通过角色 id 删除
     *
     * @param roleIds 角色 id
     */
    void deleteRoleMenusByRoleId(List<String> roleIds);

    /**
     * 通过菜单（按钮）id 删除
     *
     * @param menuIds 菜单（按钮）id
     */
    void deleteRoleMenusByMenuId(List<String> menuIds);

    /**
     * 递归删除菜单/按钮
     *
     * @param menuId menuId
     */
    void deleteRoleMenus(String menuId);
}
