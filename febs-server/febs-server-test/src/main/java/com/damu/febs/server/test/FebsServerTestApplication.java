package com.damu.febs.server.test;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.damu.febs.common.annotation.EnableFebsAuthExceptionHandler;
import com.damu.febs.common.annotation.EnableFebsOauth2FeignClient;
import com.damu.febs.common.annotation.EnableFebsServerProtect;
import com.damu.febs.common.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableFeignClients
//@EnableDiscoveryClient
//@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
//@EnableFebsAuthExceptionHandler
//@EnableFebsOauth2FeignClient
//@EnableFebsServerProtect
@MapperScan("com.damu.febs.server.test.mapper")
@FebsCloudApplication
public class FebsServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsServerTestApplication.class, args);
    }
}