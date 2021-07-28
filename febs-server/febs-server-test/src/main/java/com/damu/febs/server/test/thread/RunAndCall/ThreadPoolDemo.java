package com.damu.febs.server.test.thread.RunAndCall;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolDemo {

    public static void main(String[] args) {
        //创建线程池对象
        ExecutorService service = Executors.newFixedThreadPool(2);//包含2个线程对象
        //创建Runnable实例对象
        MyRunnable r = new MyRunnable();
//
        service.submit(r);
        service.submit(r);
        service.submit(r);
        log.info("完成Runnable");
        MyCallable c = new MyCallable();
        service.submit(c);
        service.submit(c);
        service.submit(c);
        log.info("完成Callable");
    }


}
