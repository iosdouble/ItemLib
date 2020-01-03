package com.nh.limit.common.authentication;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname ShiroSessionListener
 * @Description TODO Shiro 对于Session监听
 * @Date 2019/10/17 10:11 AM
 * @Created by nihui
 */
public class ShiroSessionListener implements SessionListener {

    /**
     * 原子性操作
     */
    private final AtomicInteger sessionCount = new AtomicInteger(0);

    @Override
    public void onStart(Session session) {

    }

    @Override
    public void onStop(Session session) {

    }

    @Override
    public void onExpiration(Session session) {

    }
}
