package com.damu.febs.server.task.config;

import com.alibaba.nacos.common.util.Md5Utils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class FeignConfig implements RequestInterceptor {

    private String key = "xxm";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Long timestamp = new Date().getTime();
        String rodJobAuth = Md5Utils.getMD5((key + timestamp).getBytes());
        requestTemplate.header("timestamp", String.valueOf(timestamp));
        requestTemplate.header("rodJobAuth", rodJobAuth);
    }

}
