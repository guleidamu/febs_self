package com.damu.febs.server.test.thread.learn.AfterThread;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {


    /**
     * 使用线程池
     */
    public void UseThreadPool(int count) {
        long startTime = System.currentTimeMillis();
        final List<Integer> l = new LinkedList<Integer>();
        /**
         * corePoolSize - 池中所保存的线程数，包括空闲线程。
         * maximumPoolSize - 池中允许的最大线程数。
         * keepAliveTime- 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
         * unit - keepAliveTime 参数的时间单位。
         * workQueue - 执行前用于保持任务的队列。此队列仅保持由 execute 方法提交的 Runnable 任务。
         *
         */
        ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(count));
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            // 內部類
            tp.execute(new Runnable() {
                @Override
                public void run() {
                    l.add(random.nextInt());
                }
            });
        }
        tp.shutdown();
        try {
            // 当我们调用pool.awaitTermination时，首先该方法会被阻塞，这时会执行子线程中的任务，
            // 子线程执行完毕后该方法仍然会被阻塞，因为shutdown()方法还未被调用，
            // 只有直到所有任务执行完毕并且shutdown请求被调用，
            // 或者参数中定义的timeout时间到达或者当前线程被打断，这几种情况任意一个发生了就会导致该方法的执行。
            tp.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("測試使用線程池:");
        System.out.println(System.currentTimeMillis() - startTime + "毫秒");
    }

    /**
     * 未使用線程池
     *
     * @param count
     *            產生隨機數的個數
     */
    public void unUseThreadPool(int count) {
        // 當前時間
        long startTime = System.currentTimeMillis();
        final List<Integer> l = new LinkedList<Integer>();
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread() {
                // 執行Run方法
                public void run() {
                    l.add(random.nextInt());
                }
            };
            // 開始線程
            thread.start();
            try {
                // 在很多情况下，主线程创建并启动子线程，如果子线程中要进行大量的耗时运算，主线程将可能早于子线程结束。
                // 如果主线程需要知道子线程的执行结果时，就需要等待子线程执行结束了。
                // 主线程可以sleep(xx),但这样的xx时间不好确定，因为子线程的执行时间不确定，join()方法比较合适这个场景。
                // 解释一下，主线程等待子线程的终止。也就是说主线程的代码块中，
                // 如果碰到了t.join()方法，此时主线程需要等待（阻塞），等待子线程结束了(Waits for this thread to die.),
                // 才能继续执行t.join()之后的代码块。
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("測試未使用線程池:");
        System.out.println(System.currentTimeMillis() - startTime + "毫秒");
        System.out.println("集合的大小:" + l.size());
    }

    public static void main(String[] args) {
        TestThreadPool testpool = new TestThreadPool();
        testpool.UseThreadPool(2000);
        testpool.unUseThreadPool(2000);

    }

}
