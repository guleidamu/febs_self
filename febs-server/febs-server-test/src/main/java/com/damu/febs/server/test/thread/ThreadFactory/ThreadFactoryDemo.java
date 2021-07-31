package com.damu.febs.server.test.thread.ThreadFactory;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadFactoryDemo {


//    public static class MyTask1 implements Runnable {
//        @Override
//        public void run() {
//            System.out.println(System.currentTimeMillis() + "Thrad ID:" + Thread.currentThread().getId());
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        MyTask1 task = new MyTask1();
//        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MICROSECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                Thread t = new Thread(r);
//                t.setDaemon(true);
//                System.out.println("创建线程" + t);
//                return t;
//            }
//        });
//        for (int i = 0; i <= 4; i++) {
//            es.submit(task);
//        }
//    }

    public static class MyTask1 implements Runnable{

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+"Thrad ID:"+Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        MyTask1 task = new MyTask1();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MICROSECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                System.out.println("创建线程"+t);
                return  t;
            }
        });
        for (int i = 0;i<=4;i++){
            es.submit(task);
        }

//        Executor executor = new Executor();
    }

}
