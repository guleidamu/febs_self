package com.damu.febs.server.test.service.impl;

import com.damu.febs.server.test.data.entity.TestLog;
import com.damu.febs.server.test.mapper.TestLogMapper;
import com.damu.febs.server.test.service.TestLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TestLogServiceImpl implements TestLogService {

    @Resource
    private TestLogMapper testLogMapper;

    @Override
    public TestLog queryTestLogById(Integer id) {
        return testLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addTestLog(TestLog testLog) {
        testLog.setCreatets(new Date());
        testLog.setLogcontent("就为了登陆而已");
        return testLogMapper.insert(testLog);
    }
}
