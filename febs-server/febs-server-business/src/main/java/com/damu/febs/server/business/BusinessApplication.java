package com.damu.febs.server.business;

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
@MapperScan("com.damu.febs.server.system.mapper")
@FebsCloudApplication
public class BusinessApplication {


    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }
}
