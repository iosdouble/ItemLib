package com.nh.limit.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nh.limit.common.entity.QueryRequest;
import com.nh.limit.system.entity.User;
import com.nh.limit.system.mapper.UserMapper;
import com.nh.limit.system.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2019/10/12 10:30 AM
 * @Created by nihui
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)

public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {


    @Override
    public User findByName(String username) {
        return null;
    }

    @Override
    public IPage<User> findUserDetail(User user, QueryRequest request) {
        return null;
    }

    @Override
    public User findUserDetail(String username) {
        return null;
    }

    @Override
    public void updateLoginTime(String username) {

    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void deleteUsers(String[] userIds) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void resetPassword(String[] usernames) {

    }

    @Override
    public void regist(String username, String password) {

    }

    @Override
    public void updatePassword(String username, String password) {

    }

    @Override
    public void updateAvatar(String username, String avatar) {

    }

    @Override
    public void updateTheme(String username, String theme, String isTab) {

    }

    @Override
    public void updateProfile(User user) {

    }
}
