package com.damu.febs.server.test.lock;

import lombok.extern.slf4j.Slf4j;

//可重入锁，什么是 “可重入”，可重入就是说某个线程已经获得某个锁，可以再次获取锁而不会出现死锁
//可重入锁又名递归锁，是指在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
//可重入锁验证
@Slf4j
public class synchronizedRead implements Runnable {

    public synchronized void get() {
        log.info("2 enter thread name-->" + Thread.currentThread().getName());
        log.info("3 get thread name-->" + Thread.currentThread().getName());
        set();
        log.info("5 leave run thread name-->" + Thread.currentThread().getName());
    }

    public synchronized void set() {
        log.info("4 set thread name-->" + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        log.info("1 run thread name-->" + Thread.currentThread().getName());
        get();
    }

    public static void main(String[] args) {
        synchronizedRead synchronizedRead = new synchronizedRead();
        for (int i = 0; i < 10; i++) {
            new Thread(synchronizedRead, "thread-" + i).start();
        }
    }
    //get()方法中顺利进入了set()方法，说明synchronized的确是可重入锁。分析打印Log，thread-0先进入get方法体，这个时候thread-3、thread-2、thread-1等待进入，但当thread-0离开时，thread-4却先进入了方法体，没有按照thread-3、thread-2、thread-1的顺序进入get方法体，说明sychronized的确是非公平锁。而且在一个线程进入get方法体后，其他线程只能等待，无法同时进入，验证了synchronized是独占锁。
    //————————————————
    //版权声明：本文为CSDN博主「tyyj90」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    //原文链接：https://blog.csdn.net/tyyj90/article/details/78236053
}
