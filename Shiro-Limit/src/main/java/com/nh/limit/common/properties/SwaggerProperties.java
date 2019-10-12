package com.nh.limit.common.properties;

import lombok.Data;

/**
 * @Classname SwaggerProperties
 * @Description TODO
 * @Date 2019/10/12 9:44 AM
 * @Created by nihui
 */
@Data
public class SwaggerProperties {
    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;
}
