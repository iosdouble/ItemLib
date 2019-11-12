package com.nh.limit.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nh.limit.system.entity.Menu;

import java.util.List;

/**
 * @Classname MenuMapper
 * @Description TODO
 * @Date 2019/10/17 10:19 AM
 * @Created by nihui
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 查找用户权限集
     *
     * @param username 用户名
     * @return 用户权限集合
     */
    List<Menu> findUserPermissions(String username);

    /**
     * 查找用户菜单集合
     *
     * @param username 用户名
     * @return 用户菜单集合
     */
    List<Menu> findUserMenus(String username);
}
