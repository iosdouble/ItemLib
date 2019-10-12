package com.nh.limit.common.authentication;

import com.nh.limit.common.properties.FebsProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname ShiroConfig
 * @Description TODO
 * @Date 2019/10/11 3:53 PM
 * @Created by nihui
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private FebsProperties febsProperties;

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password:}")
    private String password;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.database:0}")
    private int database;


    /**
     * shiro 中配置 Redis 缓存
     * @return RedisManager
     */
    private RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host+":"+port);
        if (StringUtils.isNoneBlank(password)){
            redisManager.setPassword(password);
        }
        redisManager.setTimeout(timeout);
        redisManager.setDatabase(database);
        return redisManager;
    }

    /**
     * Redis缓存管理器
     * @return
     */
    private RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        return null;
    }

    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm){
        return null;
    }


}
