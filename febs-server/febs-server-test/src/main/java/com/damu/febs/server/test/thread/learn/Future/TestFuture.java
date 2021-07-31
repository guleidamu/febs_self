package com.damu.febs.server.test.thread.learn.Future;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.*;

@Slf4j
public class TestFuture {
    public static void main(String[] args) {
        long beginTime = new Date().getTime();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Task task = new Task();
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("主线程在执行任务" + (new Date().getTime() - beginTime) );
        try {
            result.cancel(true);
            if(!result.isCancelled()){
                log.info("task运行结果" + result.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long endTime = new Date().getTime();

        log.info("时间差" + (endTime - beginTime));
        log.info("所有任务执行完毕");
    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            log.info("子线程在计算");
            Thread.sleep(10000);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
