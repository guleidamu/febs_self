package com.damu.febs.server.system.service.impl;

import com.damu.febs.server.system.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async("taskExecutor")
    public void getThread() {
        log.info("start executeAsync");
        try{
            log.info("当前线程：" +  Thread.currentThread().getName());
            Thread.sleep(6000);
        }catch(Exception e){
            e.printStackTrace();
        }
        log.info("end executeAsync");

    }
}
