package com.nh.limit.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nh.limit.system.entity.RoleMenu;
import com.nh.limit.system.mapper.RoleMenuMapper;
import com.nh.limit.system.service.IRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname RoleMenuServiceImpl
 * @Description TODO
 * @Date 2019/10/30 12:08 PM
 * @Created by nihui
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper,RoleMenu> implements IRoleMenuService {
    @Override
    @Transactional
    public void deleteRoleMenusByRoleId(List<String> roleIds) {
        this.baseMapper.delete(new QueryWrapper<RoleMenu>().lambda().in(RoleMenu::getRoleId, roleIds));
    }

    @Override
    @Transactional
    public void deleteRoleMenusByMenuId(List<String> menuIds) {
        this.baseMapper.delete(new QueryWrapper<RoleMenu>().lambda().in(RoleMenu::getRoleId, menuIds));
    }

    @Override
    @Transactional
    public void deleteRoleMenus(String menuId) {
        this.baseMapper.deleteRoleMenus(menuId);
    }
}
