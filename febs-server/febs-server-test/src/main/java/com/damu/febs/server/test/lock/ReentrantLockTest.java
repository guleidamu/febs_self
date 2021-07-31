package com.damu.febs.server.test.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockTest implements Runnable {

    //非公平锁
//    private ReentrantLock reentrantLock = new ReentrantLock();
    //公平锁
    ReentrantLock reentrantLock = new ReentrantLock(true);

    public void get(){
        System.out.println("2 enter thread name-->" + Thread.currentThread().getName());
        reentrantLock.lock();
        System.out.println("3 get thread name-->" + Thread.currentThread().getName());
        set();
        reentrantLock.unlock();
        System.out.println("5 leave run thread name-->" + Thread.currentThread().getName());
    }

    public void set(){
        reentrantLock.lock();
        System.out.println("4 set thread name-->" + Thread.currentThread().getName());
        reentrantLock.unlock();
    }

    @Override
    public void run() {
        System.out.println("1 run thread name-->" + Thread.currentThread().getName());
        get();
    }

    public static void main(String[] args){
        ReentrantLockTest test = new ReentrantLockTest();
        for(int i = 0; i < 10; i++){
            new Thread(test, "thread-" + i).start();
        }
    }

}
