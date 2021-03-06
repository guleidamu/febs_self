package com.damu.febs.server.test;

import com.damu.febs.common.annotation.EnableFebsAuthExceptionHandler;
import com.damu.febs.common.annotation.EnableFebsOauth2FeignClient;
import com.damu.febs.common.annotation.EnableFebsServerProtect;
import com.damu.febs.common.annotation.FebsCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableFebsAuthExceptionHandler
//@EnableFebsOauth2FeignClient
//@EnableFebsServerProtect
@FebsCloudApplication
public class FebsServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsServerTestApplication.class, args);
    }
}