package com.damu.febs.server.task.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.damu.febs.server.task.data.entity.Job;

import java.util.List;

/**
 * @author MrBird
 */
public interface JobMapper extends BaseMapper<Job> {

    /**
     * 获取定时任务列表
     *
     * @return 定时任务列表
     */
    List<Job> queryList();
}