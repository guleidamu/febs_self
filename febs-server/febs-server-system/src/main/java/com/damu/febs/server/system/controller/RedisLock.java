package com.damu.febs.server.system.controller;

import com.damu.febs.common.response.Result;
import com.damu.febs.common.response.ResultBuilder;
import com.damu.febs.server.system.data.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("redisLock")
@Slf4j
public class RedisLock {

    @Autowired
    private Redisson redisson;

    @PostMapping("/redissonLock")
    public Result getStudentById(@RequestBody Map student) {

        String lockKey = "product_001";
        RLock redissonLock = redisson.getLock(lockKey);

        redissonLock.lock();

        int k = 787+909;
        log.info(k+"");

        redissonLock.unlock();


        String id = (String) student.get("id");
        return ResultBuilder.success("redissonLock");
    }
}
