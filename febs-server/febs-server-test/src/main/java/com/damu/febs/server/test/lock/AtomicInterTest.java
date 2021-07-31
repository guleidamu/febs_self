package com.damu.febs.server.test.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInterTest {

    private static final int THREADS_COUNT = 20;
    //    public static int count = 0;
    public static volatile int count = 0;
    public static AtomicInteger Acount = new AtomicInteger(0);


    public static void increase() {
//        count++;
        Acount.incrementAndGet();
    }

    public synchronized static  void increaseCount() {
        count++;
//        count.incrementAndGet();
    }



    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increase();
                        increaseCount();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(count);
        System.out.println(Acount);
    }
}
