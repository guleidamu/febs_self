package com.damu.febs.server.test.thread.RunAndCall;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {

    @Override

    public Object call() {

        System.out.println("我要一个教练:call");

        try {
            Thread.sleep(2000);
            int num = 25 / 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("教练来了： "
                + Thread.currentThread().getName());
        System.out.println("教我游泳,交完后,教练回到了游泳池");

        return null;
    }

}
