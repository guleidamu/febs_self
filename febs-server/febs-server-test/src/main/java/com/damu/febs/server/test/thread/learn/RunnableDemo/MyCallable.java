package com.damu.febs.server.test.thread.learn.RunnableDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class MyCallable implements Callable<Integer> {

    static int num = 0;

    @Override
    public Integer call() throws Exception {
//        try {
//            Thread.sleep(1000);
//            log.info("开始做第{}题", num++);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return num;
        int i = 7 / 0;
        log.info("运行call");
        return 9 / 0;
    }
}
