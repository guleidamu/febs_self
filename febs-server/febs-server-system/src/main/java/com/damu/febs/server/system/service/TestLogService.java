package com.damu.febs.server.system.service;

import com.damu.febs.server.system.data.entity.TestLogBean;

import java.util.List;

public interface TestLogService {

    void batchInsert(List<TestLogBean> list);

}
