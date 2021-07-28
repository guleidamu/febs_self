package com.damu.febs.server.test.thread.learn.normal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BackUp {
    static int num = 0;
    public void run() {

        try {
            Thread.sleep(1000);
            log.info("通过runnable实现备份，日志打印:" + num++);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
