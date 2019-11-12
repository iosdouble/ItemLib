package com.nh.limit.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nh.limit.system.entity.RoleMenu;

/**
 * @Classname RoleMenuMapper
 * @Description TODO
 * @Date 2019/10/30 12:08 PM
 * @Created by nihui
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    /**
     * 递归删除菜单/按钮
     *
     * @param menuId menuId
     */
    void deleteRoleMenus(String menuId);
}
