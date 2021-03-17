package com.damu.febs.server.business.Thread.synchronizedDemo;

public class SynchronizedTest1 implements Runnable {

    public static int count;

    public SynchronizedTest1() {
        count = 0;
    }

    public  void run() {
        synchronized(this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount(){
        return count;
    }
}
