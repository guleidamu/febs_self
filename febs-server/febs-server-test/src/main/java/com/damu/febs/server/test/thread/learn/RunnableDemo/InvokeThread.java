package com.damu.febs.server.test.thread.learn.RunnableDemo;

import com.damu.febs.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;
import java.util.concurrent.Future;

@Slf4j
public class InvokeThread {

    public ThreadPoolTaskExecutor scheduleJobExecutorService() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("InvokeThread---<<<>>>");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    public static void main(String[] args) {
//        ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
        log.info(DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        long beginTime = new Date().getTime();
        InvokeThread invokeThread = new InvokeThread();
//        InvokeThread invokeThread2 = new InvokeThread();
        ThreadPoolTaskExecutor threadPoolTaskExecutor = invokeThread.scheduleJobExecutorService();
//        ThreadPoolTaskExecutor threadPoolTaskExecutor2 = invokeThread2.scheduleJobExecutorService();
        BackUpRunnable backUpRunnable = new BackUpRunnable();
//        WorkRunnable workRunnable = new WorkRunnable();
        MyCallable myCallable = new MyCallable();
        try {
            for (int i = 0; i < 20; i++) {
//                ThreadPoolTaskExecutor threadPoolTaskExecutor = invokeThread.scheduleJobExecutorService();
//                threadPoolTaskExecutor.execute(backUpRunnable);
//                threadPoolTaskExecutor.execute(workRunnable);
                log.info("i??????<<<" + i);
//                Future<Integer> submit = threadPoolTaskExecutor.submit(myCallable);
                Future<?> future = threadPoolTaskExecutor.submit(backUpRunnable);
//                //get()???????????????????????????????????????????????????????????????????????????????????????????????????
//                //get(long timeout, TimeUnit unit)????????????????????????????????????????????????????????????????????????????????????TimeoutException
//                future.get();
//                submit.get();
                log.info("i??????" + i);
//                Future<?> workFuture = threadPoolTaskExecutor.submit(workRunnable);
//                workFuture.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = new Date().getTime();

        log.info("?????????" + (endTime - beginTime));
        log.info("?????????");

        String ppid = "CN082V4CCH30083R-0727001";

        String pn = ppid.substring(3, 8);
        System.out.println("pn:" + pn);
        threadPoolTaskExecutor.shutdown();

    }
}
