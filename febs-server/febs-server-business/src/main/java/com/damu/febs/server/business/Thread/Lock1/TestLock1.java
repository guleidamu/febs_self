package com.damu.febs.server.business.Thread.Lock1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock1 {
    public static void main(String[] args) {
//        Ticket ticket = new Ticket();
//        new Thread(ticket, "1号窗口").start();
//        new Thread(ticket, "2号窗口").start();
//        new Thread(ticket, "3号窗口").start();
        TicketThread meili0 = new TicketThread("Thread-0 ");
        meili0.start();
        TicketThread meili1 = new TicketThread("Thread-1 ");
        meili1.start();
        TicketThread meili2 = new TicketThread("Thread-2 ");
        meili2.start();
    }
}

class Ticket implements Runnable {

    private int tick = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (tick > 0) {
            try {
                Thread.sleep(100);
                lock.lock();
                if (tick > 0) {
                    System.out.println(Thread.currentThread().getName() + "完成售票，余票" + --tick);
                }
                System.out.println(">>>>>");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}


class TicketThread extends Thread {
    private Thread t;
    private String threadName;

    TicketThread(String threadName) {
        this.threadName = threadName;
    }
    private static int num = 1;
    private static Object object = new Object();


    public void run() {
        while (num < 100) {
            try {
                Thread.sleep(100);
                synchronized (object) {
                    if (num < 100) {
                        System.out.println("线程" + Thread.currentThread().getName() + " 当前卖出第" + num + "张,,::" );
                        num++;
                    } else {
                        System.out.println("票卖完了");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    private static int num = 1;
//    private static Object obj = new Object();
//
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                Thread.sleep(300);//单位是毫秒值
//                synchronized (obj) {
//                    if (num < 16) {
//                        System.out.println("线程" + Thread.currentThread().getName() + ":当前卖出:" + num + "号票..");
//                        num++;    //每卖出1张 ,要减去1张
//                    } else {
//                        System.out.println("当前车次的票已售完...");
//                        System.exit(0); //退出程序
//                    }
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}

