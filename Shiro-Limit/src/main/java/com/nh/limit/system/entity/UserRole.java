package com.nh.limit.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Classname UserRole
 * @Description 用户角色表
 * @Date 2019/10/30 10:59 AM
 * @Created by nihui
 */
@Data
@TableName("t_user_role")
public class UserRole {
    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;
}
