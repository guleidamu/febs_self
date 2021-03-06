package com.damu.febs.auth;

import com.damu.febs.common.annotation.EnableFebsAuthExceptionHandler;
import com.damu.febs.common.annotation.EnableFebsLettuceRedis;
import com.damu.febs.common.annotation.EnableFebsServerProtect;
import com.damu.febs.common.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication
//@EnableFebsAuthExceptionHandler
//@EnableFebsServerProtect
@MapperScan("com.damu.febs.auth.mapper")
@EnableFebsLettuceRedis
@FebsCloudApplication
public class FebsAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(FebsAuthApplication.class, args);
    }
}