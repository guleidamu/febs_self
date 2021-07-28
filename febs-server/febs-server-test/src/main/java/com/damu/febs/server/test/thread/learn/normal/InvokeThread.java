package com.damu.febs.server.test.thread.learn.normal;

import com.damu.febs.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;

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
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    public static void main(String[] args) {
//        ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
        log.info(DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        long beginTime = new Date().getTime();
//        InvokeThread invokeThread = new InvokeThread();
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = invokeThread.scheduleJobExecutorService();
        BackUp backUp = new BackUp();
        Work work = new Work();
        try {
            for (int i = 0; i < 20; i++) {
////                ThreadPoolTaskExecutor threadPoolTaskExecutor = invokeThread.scheduleJobExecutorService();
//                Future<?> future = threadPoolTaskExecutor.submit(backUp);
//                future.get();
//                Future<?> workFuture = threadPoolTaskExecutor.submit(work);
//                workFuture.get();
                backUp.run();
                work.run();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = new Date().getTime();

        log.info("时间差" + (endTime - beginTime));
        log.info("跑完了");

        String ppid = "CN082V4CCH30083R-0727001";

        String pn = ppid.substring(3, 8);
        System.out.println("pn:" + pn);
//        threadPoolTaskExecutor.shutdown();

    }
}
