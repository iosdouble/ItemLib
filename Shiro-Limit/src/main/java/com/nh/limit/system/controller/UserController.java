package com.nh.limit.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.nh.limit.common.annotation.ControllerEndpoint;
import com.nh.limit.common.controller.BaseController;
import com.nh.limit.common.entity.FebsResponse;
import com.nh.limit.common.entity.QueryRequest;
import com.nh.limit.common.exception.FebsException;
import com.nh.limit.common.utils.MD5Util;
import com.nh.limit.system.entity.User;
import com.nh.limit.system.service.IUserService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2019/10/30 4:23 PM
 * @Created by nihui
 */
@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @GetMapping("{username}")
    public User getUser(@NotBlank(message = "{required}") @PathVariable String username) {
        return this.userService.findUserDetail(username);
    }

    @GetMapping("check/{username}")
    public boolean checkUserName(@NotBlank(message = "{required}") @PathVariable String username, String userId) {
        return this.userService.findByName(username) == null || StringUtils.isNotBlank(userId);
    }

    @GetMapping("list")
    @RequiresPermissions("user:view")
    public FebsResponse userList(User user, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.userService.findUserDetail(user, request));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("user:add")
    @ControllerEndpoint(operation = "新增用户", exceptionMessage = "新增用户失败")
    public FebsResponse addUser(@Valid User user) {
        this.userService.createUser(user);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{userIds}")
    @RequiresPermissions("user:delete")
    @ControllerEndpoint(operation = "删除用户", exceptionMessage = "删除用户失败")
    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) {
        String[] ids = userIds.split(StringPool.COMMA);
        this.userService.deleteUsers(ids);
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @RequiresPermissions("user:update")
    @ControllerEndpoint(operation = "修改用户", exceptionMessage = "修改用户失败")
    public FebsResponse updateUser(@Valid User user) {
        if (user.getUserId() == null)
            throw new FebsException("用户ID为空");
        this.userService.updateUser(user);
        return new FebsResponse().success();
    }

    @PostMapping("password/reset/{usernames}")
    @RequiresPermissions("user:password:reset")
    @ControllerEndpoint(exceptionMessage = "重置用户密码失败")
    public FebsResponse resetPassword(@NotBlank(message = "{required}") @PathVariable String usernames) {
        String[] usernameArr = usernames.split(StringPool.COMMA);
        this.userService.resetPassword(usernameArr);
        return new FebsResponse().success();
    }

    @PostMapping("password/update")
    @ControllerEndpoint(exceptionMessage = "修改密码失败")
    public FebsResponse updatePassword(
            @NotBlank(message = "{required}") String oldPassword,
            @NotBlank(message = "{required}") String newPassword) {
        User user = getCurrentUser();
        if (!StringUtils.equals(user.getPassword(), MD5Util.encrypt(user.getUsername(), oldPassword))) {
            throw new FebsException("原密码不正确");
        }
        userService.updatePassword(user.getUsername(), newPassword);
        return new FebsResponse().success();
    }

    @GetMapping("avatar/{image}")
    @ControllerEndpoint(exceptionMessage = "修改头像失败")
    public FebsResponse updateAvatar(@NotBlank(message = "{required}") @PathVariable String image) {
        User user = getCurrentUser();
        this.userService.updateAvatar(user.getUsername(), image);
        return new FebsResponse().success();
    }

    @PostMapping("theme/update")
    @ControllerEndpoint(exceptionMessage = "修改系统配置失败")
    public FebsResponse updateTheme(String theme, String isTab) {
        User user = getCurrentUser();
        this.userService.updateTheme(user.getUsername(), theme, isTab);
        return new FebsResponse().success();
    }

    @PostMapping("profile/update")
    @ControllerEndpoint(exceptionMessage = "修改个人信息失败")
    public FebsResponse updateProfile(User user) throws FebsException {
        User currentUser = getCurrentUser();
        user.setUserId(currentUser.getUserId());
        this.userService.updateProfile(user);
        return new FebsResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("user:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, User user, HttpServletResponse response) {
        List<User> users = this.userService.findUserDetail(user, queryRequest).getRecords();
        ExcelKit.$Export(User.class, response).downXlsx(users, false);
    }
}
