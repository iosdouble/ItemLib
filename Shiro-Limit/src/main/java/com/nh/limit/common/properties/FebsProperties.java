package com.nh.limit.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Classname FebsProperties
 * @Description TODO
 * @Date 2019/10/12 9:41 AM
 * @Created by nihui
 */
@Data
@SpringBootConfiguration
@PropertySource("classpath:febs.properties")
@ConfigurationProperties(prefix = "febs")
public class FebsProperties {

    private ShiroProperties shiro = new ShiroProperties();
    private boolean autoOpenBrowser = true;
    private String[] autoOpenBrowserEnv = {};
    private SwaggerProperties swagger = new SwaggerProperties();
}
