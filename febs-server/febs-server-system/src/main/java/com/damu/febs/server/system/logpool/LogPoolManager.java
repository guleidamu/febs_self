package com.damu.febs.server.system.logpool;

import com.damu.febs.server.system.data.entity.TestLogBean;
import com.damu.febs.server.system.service.TestLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@Scope("singleton")
public class LogPoolManager {

    @Autowired
    private TestLogService testLogService;

    /**
     * 日志队列的最大容量
     */
    private int MAX_QUEUE_SIZE = 100;


    /**
     *  日志批量插入的数量，10条日志
     */
    private int BATCH_SIZE = 10;

    /**
     * 线程睡眠时间，具体时间需要结合项目实际情况，单位毫秒
     */
    private int SLEEP_TIME = 500;

    /**
     * 日志插入执行的最大的时间间隔，单位毫秒
     */
    private long MAX_EXE_TiME = 5000;

    /**
     * 创建一个单线程的线程池
     */
    private ExecutorService logManagerThreadPool = Executors.newSingleThreadExecutor();

    /**
     * 创建一个定长的线程池，线程数量为（java虚拟机可用的处理器数量 * 2 + 20 ）
     */
    private ExecutorService logWorkerThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 20);

    /**
     * 任务队列，存放日志内容
     */
    private BlockingQueue<TestLogBean> queue;

    /**
     * Boolean 原子变量类
     */
    private AtomicBoolean run = new AtomicBoolean(true);

    /**
     * 整型 原子变量类，记录 任务队列 中的任务数量
     */
    private AtomicInteger logCount = new AtomicInteger(0);


    /**
     * 上一次执行日志插入时的时间
     */
    private long lastExecuteTime;

    /**
     * 初始化
     */
    public void init(){
        //基于链表的双向阻塞队列，在队列的两端都可以插入和移除元素，是线程安全的，多线程并发下效率更高
        queue = new LinkedBlockingDeque<>(MAX_QUEUE_SIZE);
        lastExecuteTime = System.currentTimeMillis();
        log.info("LogPoolManager init successfully.........");

        logManagerThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (run.get()){
                    //线程休眠，具体时间根据项目的实际情况配置
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        log.error("log Manager Thread sleep fail ", e);
                    }
                    if(logCount.get() >= BATCH_SIZE||(System.currentTimeMillis() - lastExecuteTime) > MAX_EXE_TiME) {
                        if(logCount.get()>0){
                            log.info("begin brain log queue to database...");
                           List<TestLogBean> list = new ArrayList<>();
                            /**
                             * drainTo (): 一次性从BlockingQueue获取所有可用的数据对象（还可以指定获取数据的个数），
                             *  通过该方法，可以提升获取数据效率；不需要多次分批加锁或释放锁。
                             *  将取出的数据放入指定的list集合中
                             */
                           queue.drainTo(list);
                           //任务队列中人物数量设置为0
                           logCount.set(0);
                           logWorkerThreadPool.execute(new InsertThread(testLogService,list));
                           log.info("end drain log queue to database...");
                        }
                        //获取当前执行的时间
                        lastExecuteTime = System.currentTimeMillis();
                    }
                }
                log.info("LogPoolManager shutdown successfully");
            }
        });
    }

    /**
     * 将日志放入队列中
     */
    public void addLog(TestLogBean testLogBean) throws Exception{
        if(logCount.get()>=MAX_QUEUE_SIZE){
            throw new Exception("rejected ..Log count exceed log queue's max size!");
        }
        //将日志放入 任务队列中，放入
        this.queue.offer(testLogBean);
        //队列中的任务数量 +1
        logCount.incrementAndGet();
    }

    /**
     * 关闭线程池
     */
    public void shutdown(){
        log.info("LogPoolManager Thread Pool shutdown...");
        run.set(false);
        //关闭线程池
        logWorkerThreadPool.shutdownNow();
        logManagerThreadPool.shutdownNow();
    }



}
