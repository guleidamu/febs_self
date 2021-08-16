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
import java.util.concurrent.*;

public class TestThread {

//    public static void main(String[] args) {
//
//        for (int i = 0; i < 10; i++) {
//            final int task = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("task" + task);
//                }
//            }).start();
//        }
//    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> future = executorService.submit(new Task());
        Integer integer = future.get();
        System.out.println(integer);
        executorService.shutdown();
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程开始计算");
            int sum = 0;
            for (int i = 0; i <= 100; i++) {
                sum += i;
            }
            return sum;
        }
    }

}
