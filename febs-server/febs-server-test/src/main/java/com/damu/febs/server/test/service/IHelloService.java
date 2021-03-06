package com.damu.febs.server.test.service;

import com.damu.febs.common.entity.FebsServerConstant;
import com.damu.febs.server.test.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value = FebsServerConstant.FEBS_SERVER_SYSTEM, contextId = "helloServiceClient", fallbackFactory = HelloServiceFallback.class)
//public interface IHelloService {
//
//    @GetMapping("hello")
//    String hello(@RequestParam String name);
//}

//@FeignClient(value = "FEBS-Server-System", contextId = "helloServiceClient", fallbackFactory = HelloServiceFallback.class)
//public interface IHelloService {
@FeignClient(value = FebsServerConstant.FEBS_SERVER_SYSTEM, contextId = "helloServiceClient", fallbackFactory = HelloServiceFallback.class)
public interface IHelloService {
    @GetMapping("hello")
    String hello(@RequestParam("name") String name);
}