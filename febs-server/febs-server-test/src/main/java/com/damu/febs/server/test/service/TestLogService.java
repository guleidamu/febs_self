package com.damu.febs.server.test.service;

import com.damu.febs.server.test.data.entity.TestLog;

public interface TestLogService {

    TestLog queryTestLogById(Integer id);

    int addTestLog(TestLog testLog);
}
