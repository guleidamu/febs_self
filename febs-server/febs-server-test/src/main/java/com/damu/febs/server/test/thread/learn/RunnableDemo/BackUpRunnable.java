package com.damu.febs.server.test.thread.learn.RunnableDemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BackUpRunnable implements Runnable {
    static int num = 0;

    static byte[] lock = new byte[0];

    @Override
    public void run() {
//        synchronized (lock) {
//            try {
//                Thread.sleep(1000);
//                log.info("通过runnable实现备份，日志打印:" + num++);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        log.info("运行run" + 9 / 0);

    }
}
