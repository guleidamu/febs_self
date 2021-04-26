package com.damu.febs.server.system.mapper;

import com.damu.febs.server.system.data.entity.TestLogBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestLogDao {
    /**
     *  批量进行日志插入
     * @param list  测试日志集合
     */
    void batchInsert(List<TestLogBean> list);
}
