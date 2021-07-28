package com.damu.febs.server.test.thread.learn.AfterThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AfterThread {

    public static void main(String[] args) {
        // 1.不使用线程池情况
//        notUseThreadPool();
        // 2.使用线程池的情况
        useThreadPool();
    }

    /**
     * 不使用线程池情况
     */
    public static void notUseThreadPool() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println("正在执行第" + (i + 1) + "次循环");
            bubbleSort();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
    }

    /**
     * 使用线程池的情况
     */
    public static void useThreadPool() {
        long startTime = System.currentTimeMillis();
        //创建使用固定线程数的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        // 判断线程是否全部执行结束
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    System.out.println("正在执行第" + (finalI + 1) + "个线程");
                    bubbleSort();
                } catch (Exception e) {
                    System.out.println("线程池异常A" + e.getMessage());
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            // 等待线程全部执行结束
            countDownLatch.await();
        } catch (Exception e) {
            System.out.println("countDownLatch.await" + e.getMessage());
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
    }

    /**
     * 冒泡排序用来模拟一个任务
     */
    public static void bubbleSort() {
        int[] array = randomCommon(0, 10000000, 50000);
        int swap;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swap;
                }
            }
        }
    }

    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    public static int[] randomCommon(int min, int max, int n) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }
}
