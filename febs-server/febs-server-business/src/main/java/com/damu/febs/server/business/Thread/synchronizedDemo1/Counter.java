package com.damu.febs.server.business.Thread.synchronizedDemo1;

public class Counter implements Runnable {

    private int count;

    public Counter() {
        count = 0;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        if("A".equals(name)){
            countAdd();
        } else if("B".equals(name)){
            printCount();
        }
    }

    public void countAdd() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": " +(count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printCount(){
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "count: " +(count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
