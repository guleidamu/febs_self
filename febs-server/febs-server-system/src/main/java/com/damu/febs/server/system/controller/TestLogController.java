package com.damu.febs.server.system.controller;

import com.damu.febs.server.system.data.entity.TestLogBean;
import com.damu.febs.server.system.logpool.LogPoolManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1/api")
public class TestLogController {

    @Autowired
    private LogPoolManager logPoolManager;

    @GetMapping("/log/test")
    public void logTest(){
        TestLogBean testLogBean = new TestLogBean();
        testLogBean.setLogContent("test log, test log");
        //将业务日志放入队列中，然后使用线程异步批量入库，以提升接口的响应速度
        try {
            logPoolManager.addLog(testLogBean);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage(),e);
        }
        log.info("log offer queue success!");
    }

}
