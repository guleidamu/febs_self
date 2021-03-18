package com.damu.febs.server.business;

import com.damu.febs.common.annotation.EnableFebsLettuceRedis;
import com.damu.febs.common.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableFebsAuthExceptionHandler
//@EnableFebsServerProtect
//因为redis的操作类放在common上面，common没有启动类，所以启动的时候没办法将redis注入到ioc，
// 通过enablefebsLettuceRedis，可以将redis注入到ioc容器
@EnableFebsLettuceRedis
@MapperScan("com.damu.febs.server.business.mapper")
@FebsCloudApplication
public class BusinessApplication {


    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }
}
