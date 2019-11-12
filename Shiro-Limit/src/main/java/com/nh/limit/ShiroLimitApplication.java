package com.nh.limit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@MapperScan("com.nh.limit.*.mapper")
public class ShiroLimitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroLimitApplication.class, args);
    }

}
