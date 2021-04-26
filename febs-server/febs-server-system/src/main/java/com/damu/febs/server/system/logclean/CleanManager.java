package com.damu.febs.server.system.logclean;

import cn.hutool.setting.dialect.Props;
import com.damu.febs.server.system.data.bean.LogCleanBean;
import com.damu.febs.server.system.service.LogCleanService;
import com.damu.febs.server.system.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component("cleanManager")
public class CleanManager {

    @Autowired
    private LogCleanService logCleanService;

    private static ExecutorService cleanManagerThreadPool;
    private static Props props = new Props("application-cleanLog.properties");

    private static LogCleanBean logCleanBean = new LogCleanBean();

    static {
        // 获取配置的线程数大小
        int threadNum = props.getInt("threads.pool.num");
        // 创建 固定线程数的线程池
        cleanManagerThreadPool = Executors.newFixedThreadPool(threadNum);
        // 每次清理日志时的数据量
        logCleanBean.setBatchCleanCount(props.getInt("log.clean.batchCount"));
        // 清理的表
        logCleanBean.setTableName(props.getStr("log.clean.table"));
        // 根据日志清理的字段
        logCleanBean.setFieldName(props.getStr("log.clean.filed"));
        // 什么时间的日志可以被清理
        logCleanBean.setMinTime(DateUtil.getBeforeTime(props.getInt("log.clean.dateNum")));
    }

    /**
     *  多线程清理日志启动
     */
    public void cleanLogStart(){

        // 循环进行日志清理的次数
        int whileNum = props.getInt("log.clean.batchNum");
        LogCleanBean logClean = null;

        while (whileNum > 0){

            // 查询符合每次删除数据量的时间段
            List<String> list = logCleanService.selectTime(logCleanBean);
            if (list != null && list.size() > 0){
                logClean = new LogCleanBean();
                logClean.setTableName(logCleanBean.getTableName());
                logClean.setFieldName(logCleanBean.getFieldName());
                // 获取可以删除日志的最小生成时间
                logClean.setMinTime(list.get(list.size()-1));
                // 获取可以删除日志的最大生成时间
                logClean.setMaxTime(list.get(0));
                logCleanBean.setMinTime(logClean.getMinTime());

                // 此次查询已经不满足设置的每次清理的数据量大小了，说明已经清理干净了
                if (list.size() < logCleanBean.getBatchCleanCount()){
                    whileNum = 0;
                }else {
                    // 清理次数进行递减
                    --whileNum;
                }
            }else {
                break;
            }
            // 进行多线程处理
            cleanManagerThreadPool.execute(new CleanThread(this.logCleanService, logClean));
        }
    }

    /**
     * 关闭线程池
     */
    public void shutdown(){
        log.info("CleanManager Thread Pool shutdown...");
        /**
         *  关闭线程池
         *  调用了shutdownNow()方法后，线程池将不能接受新任务，也不会处理队列中的任务，并且会中断正在处理任务的线程
         */
        cleanManagerThreadPool.shutdownNow();
    }


}
