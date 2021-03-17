package com.damu.febs.server.business.Thread.synchronizedDemo;


public class Demo0 {
    public static void main(String[] args) {
        //test1
        SynchronizedTest1 synchronizedTest1 = new SynchronizedTest1();
        SynchronizedTest1 synchronizedTest2 = new SynchronizedTest1();
        Thread thread1 = new Thread(synchronizedTest1);
        Thread thread2 = new Thread(synchronizedTest2);

//        SynchronizedTest1 s = new SynchronizedTest1();
//        Thread thread1 = new Thread(s);
//        Thread thread2 = new Thread(s);

        thread1.start();
        thread2.start();
    }
}
