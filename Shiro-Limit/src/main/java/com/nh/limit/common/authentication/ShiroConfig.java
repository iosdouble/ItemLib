package com.nh.limit.common.authentication;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.nh.limit.common.properties.FebsProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;

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
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置登陆的url
        shiroFilterFactoryBean.setLoginUrl(febsProperties.getShiro().getLoginUrl());
        //设置登陆成功之后跳转的url
        shiroFilterFactoryBean.setSuccessUrl(febsProperties.getShiro().getSuccessUrl());
        //设置未授权
        shiroFilterFactoryBean.setUnauthorizedUrl(febsProperties.getShiro().getUnauthorizedUrl());

        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //设置免认证的url
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(febsProperties.getShiro().getAnonUrl(),",");
        for (String url: anonUrls) {
            filterChainDefinitionMap.put(url,"anon");
        }
        //设置退出过滤器，具体的退出代码Shiro已经实现只需要调用就可以了
        filterChainDefinitionMap.put(febsProperties.getShiro().getLogoutUrl(),"logout");

        //除了以上的配置以外其他的东西都要通过认证才能以方法
        filterChainDefinitionMap.put("/**","user");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //配置Security Manager并且注入shiroRealm
        securityManager.setRealm(shiroRealm);
        //设置 Shiro Session管理器
        securityManager.setSessionManager(sessionManager());
        //设置缓存管理类 cacheManager
        securityManager.setCacheManager(cacheManager());
        //设置RemberMeCookie
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     *  rememberMe Cookie 效果是重开浏览器后无需重新登录
     * @return
     */
    private SimpleCookie rememberMeCookie(){
        //设置Cookie 名称，对应的login.html 页面的<input type = 'checkbox' name = 'rememberMe'>
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //设置 Cookie 的过期时间，单位为秒
        cookie.setMaxAge(febsProperties.getShiro().getCookieTimeout());
        return cookie;
    }


    /**
     * Cookie 管理对象
     * @return
     */
    private RememberMeManager rememberMeManager() {

        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe Cookie加密密钥
        String encryptKey = "febs_shiro_key";
        byte[] encryptKeyBytes = encryptKey.getBytes(StandardCharsets.UTF_8);
        String rememberKey = Base64Utils.encodeToString(Arrays.copyOf(encryptKeyBytes,16));
        cookieRememberMeManager.setCipherKey(Base64.decode(rememberKey));
        return null;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * 用于开启 Thymeleaf 中的 shiro 标签的使用
     *
     * @return ShiroDialect shiro 方言对象
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * Session 管理对象
     *
     * @return
     */
    private SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());
        // 设置 session超时时间
        sessionManager.setGlobalSessionTimeout(febsProperties.getShiro().getSessionTimeout() * 1000L);
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }


}
