package com.damu.febs.server.task.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.damu.febs.common.entity.FebsConstant;
import com.damu.febs.common.entity.QueryRequest;
import com.damu.febs.server.task.data.entity.JobLog;
import org.springframework.scheduling.annotation.Async;

/**
 * @author MrBird
 */
public interface IJobLogService extends IService<JobLog> {

    /**
     * 获取定时任务日志分页数据
     *
     * @param request request
     * @param jobLog  jobLog
     * @return 定时任务日志分页数据
     */
    IPage<JobLog> findJobLogs(QueryRequest request, JobLog jobLog);

    /**
     * 保存定时任务日志
     *
     * @param log 定时任务日志
     */
    @Async(FebsConstant.ASYNC_POOL)
    void saveJobLog(JobLog log);

    /**
     * 删除定时任务日志
     *
     * @param jobLogIds 定时任务日志id数组
     */
    void deleteJobLogs(String[] jobLogIds);
}
