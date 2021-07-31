package com.damu.febs.server.test.thread.ticket;

public class ticket implements Runnable {

    private static int total = 100;

    private static byte[] lock = new byte[0];

    private static int num = 0;


    @Override
    public void run() {

        while (total > 0) {
            synchronized (lock) {
                if (total > 0) {
                    try {
                        System.out.println("当前线程" + Thread.currentThread().getName() + " 还剩下" + --total + " 已购买" + ++num);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("当前线程" + Thread.currentThread().getName() + " 卖完了");
//                    break;
                }
            }
        }
    }
}
