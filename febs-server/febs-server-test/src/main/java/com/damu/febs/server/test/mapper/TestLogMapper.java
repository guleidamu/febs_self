package com.damu.febs.server.test.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.damu.febs.server.test.data.entity.TestLog;

@DS("second")
public interface TestLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestLog record);

    int insertSelective(TestLog record);

    TestLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestLog record);

    int updateByPrimaryKey(TestLog record);
}