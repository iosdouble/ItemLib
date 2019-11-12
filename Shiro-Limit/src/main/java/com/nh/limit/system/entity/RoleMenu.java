package com.nh.limit.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname RoleMenu
 * @Description TODO
 * @Date 2019/10/30 11:49 AM
 * @Created by nihui
 */
@Data
@TableName("t_role_menu")
public class RoleMenu implements Serializable {
    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;

    /**
     * 菜单/按钮ID
     */
    @TableField("MENU_ID")
    private Long menuId;
}
