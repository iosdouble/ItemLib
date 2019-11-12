package com.nh.limit.system.controller;

import com.nh.limit.common.authentication.ShiroHelper;
import com.nh.limit.common.controller.BaseController;
import com.nh.limit.common.entity.FebsConstant;
import com.nh.limit.common.utils.DateUtil;
import com.nh.limit.common.utils.FebsUtil;
import com.nh.limit.system.entity.User;
import com.nh.limit.system.service.IUserService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ViewController
 * @Description TODO
 * @Date 2019/10/22 6:07 PM
 * @Created by nihui
 */
@Controller("systemView")
public class ViewController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ShiroHelper shiroHelper;

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        if (FebsUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(FebsUtil.view("login"));
            return mav;
        }
    }

    @GetMapping("unauthorized")
    public String unauthorized(){
        return FebsUtil.view("error/403");
    }

    @GetMapping("/")
    public String redirectIndex(){
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model){
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        User user = super.getCurrentUser();
        User currentUserDetail = userService.findByName(user.getUsername());
        model.addAttribute("user",currentUserDetail);
        model.addAttribute("permissions",authorizationInfo.getStringPermissions());
        model.addAttribute("roles",authorizationInfo.getRoles());
        return "index";
    }

    /**
     * 处理登出请求
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return FebsUtil.view("layout");
    }

    /**
     * 用户密码更新请求
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return FebsUtil.view("system/user/passwordUpdate");
    }

    /**
     * 用户配置
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return FebsUtil.view("system/user/userProfile");
    }

    /**
     *
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return FebsUtil.view("system/user/avatar");
    }

    /**
     *
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return FebsUtil.view("system/user/profileUpdate");
    }

    /**
     *
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return FebsUtil.view("system/user/user");
    }

    /**
     *
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return FebsUtil.view("system/user/userAdd");
    }

    /**
     *
     * @param username
     * @param model
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return FebsUtil.view("system/user/userDetail");
    }

    /**
     *
     * @param username
     * @param model
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return FebsUtil.view("system/user/userUpdate");
    }

    /**
     *
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return FebsUtil.view("system/role/role");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return FebsUtil.view("system/menu/menu");
    }

    /**
     *
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "system/dept")
    @RequiresPermissions("dept:view")
    public String systemDept() {
        return FebsUtil.view("system/dept/dept");
    }

    /**
     *
     * @return
     */
    @RequestMapping(FebsConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return FebsUtil.view("index");
    }

    /**
     *
     * @return
     */
    @GetMapping(FebsConstant.VIEW_PREFIX + "404")
    public String error404() {
        return FebsUtil.view("error/404");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "403")
    public String error403() {
        return FebsUtil.view("error/403");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "500")
    public String error500() {
        return FebsUtil.view("error/500");
    }

    /**
     *
     * @param username
     * @param model
     * @param transform
     */
    private void resolveUserModel(String username, Model model, Boolean transform) {
        User user = userService.findByName(username);
        model.addAttribute("user", user);
        if (transform) {
            String ssex = user.getSex();
            if (User.SEX_MALE.equals(ssex)) user.setSex("男");
            else if (User.SEX_FEMALE.equals(ssex)) user.setSex("女");
            else user.setSex("保密");
        }
        if (user.getLastLoginTime() != null)
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }

}
