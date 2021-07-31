package com.damu.febs.server.test.thread;

import lombok.Data;


public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private String name;

    public static void main(String[] args) {
        ThreadLocalTest tmp = new ThreadLocalTest();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                tmp.setName(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() +
                        "\t拿到数据" + tmp.getName());
            });
            thread.setName("Thread-" + i);
            thread.start();
        }
    }

    public String getName() {
        return threadLocal.get();
    }

    public void setName(String name) {
        threadLocal.set(name);
    }
}
