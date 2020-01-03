package com.nh.limit.common.function;

import com.nh.limit.common.exception.RedisConnectException;

/**
 * @Classname JedisExecutor
 * @Description TODO Redis 处理
 * @Date 2019/10/22 5:20 PM
 * @Created by nihui
 */
@FunctionalInterface
public interface JedisExecutor<T,R> {
    R excute(T t) throws RedisConnectException;
}
