package com.damu.febs.server.business.Thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class ThreadClient {

    @Autowired
    @Qualifier(value = "crawlExecutorPool")
    private ExecutorService pool;

    public void crawlRedisQueue() {
        for (int i = 0; i < 2000000; i++) {
            pool.execute(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "======定时任务执行完成======");
            });
        }
    }
//
//    public static void main(String[] args) {
//        Client client = new Client();
//        client.crawlRedisQueue();
//    }
}
