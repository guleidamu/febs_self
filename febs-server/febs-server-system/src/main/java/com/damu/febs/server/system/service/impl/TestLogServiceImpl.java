package com.damu.febs.server.system.service.impl;

import com.damu.febs.server.system.data.entity.TestLogBean;
import com.damu.febs.server.system.mapper.TestLogDao;
import com.damu.febs.server.system.service.TestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testLogService")
public class TestLogServiceImpl implements TestLogService{

    @Autowired
    private TestLogDao testLogDao;

    @Override
    public void batchInsert(List<TestLogBean> list) {
        testLogDao.batchInsert(list);
    }
}
