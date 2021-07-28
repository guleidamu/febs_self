package com.damu.febs.server.test.thread.learn.RunnableDemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkRunnable implements Runnable {

    static int num = 0;
    static byte[] lock = new byte[0];

    @Override
    public void run() {
        synchronized (lock){
            try {
                Thread.sleep(1000);
                log.info("开始做第{}题", num++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
