package com.damu.febs.server.business.Thread.synchronizedDemo;

public class Demo1 {

    public static void main(String args[]) {
//     test01
//        SyncThread s1 = new SyncThread();
//        SyncThread s2 = new SyncThread();
//
//        Thread t1 = new Thread(s1);
//        Thread t2 = new Thread(s2);
//　　　　//test02
        SyncThread s = new SyncThread();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);

        t1.start();
        t2.start();
    }
}
