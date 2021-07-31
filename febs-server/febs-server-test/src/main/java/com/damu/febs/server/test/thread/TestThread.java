package com.damu.febs.server.test.thread;

public class TestThread {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            final int task = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("task" + task);
                }
            }).start();
        }
    }
}
