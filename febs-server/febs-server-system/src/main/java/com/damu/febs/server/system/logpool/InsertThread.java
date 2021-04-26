package com.damu.febs.server.system.logpool;

import com.damu.febs.server.system.data.entity.TestLogBean;
import com.damu.febs.server.system.service.TestLogService;

import java.util.List;

public class InsertThread implements Runnable {

    private TestLogService testLogService;

    private List<TestLogBean> list;

    public InsertThread(TestLogService testLogService, List<TestLogBean> list) {
        this.testLogService = testLogService;
        this.list = list;
    }

    @Override
    public void run() {
        testLogService.batchInsert(list);
        // hellp GC
        list = null;
    }
}
