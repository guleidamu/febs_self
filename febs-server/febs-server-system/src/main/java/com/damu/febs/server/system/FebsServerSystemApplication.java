package com.damu.febs.server.system;

import com.damu.febs.common.annotation.EnableFebsAuthExceptionHandler;
import com.damu.febs.common.annotation.EnableFebsServerProtect;
import com.damu.febs.common.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableFebsAuthExceptionHandler
//@EnableFebsServerProtect
@MapperScan("com.damu.febs.server.system.mapper")
@FebsCloudApplication
public class FebsServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsServerSystemApplication.class, args);
    }

//    @Bean
//    public Redisson redisson() {
//        //此为单机模式
//        Config config = new Config();
////        config.useSingleServer().setAddress("redis://8.140.152.182:6379").setPassword("meiliaichangge").setDatabase(5);
////        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(5);
//        return (Redisson) Redisson.create(config);
//    }
}