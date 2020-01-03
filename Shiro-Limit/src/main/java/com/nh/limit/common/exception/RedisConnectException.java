package com.nh.limit.common.exception;

/**
 * @Classname RedisConnectException
 * @Description TODO Redis 异常处理
 * @Date 2019/10/22 4:31 PM
 * @Created by nihui
 */
public class RedisConnectException extends Exception {
    public RedisConnectException(String message) {
        super(message);
    }
}
