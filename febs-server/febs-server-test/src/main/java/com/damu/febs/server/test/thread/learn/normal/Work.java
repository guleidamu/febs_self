package com.damu.febs.server.test.thread.learn.normal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Work {

    static int num = 0;


    public void run() {

        try {
            Thread.sleep(1000);
            log.info("开始做第{}题", num++);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
