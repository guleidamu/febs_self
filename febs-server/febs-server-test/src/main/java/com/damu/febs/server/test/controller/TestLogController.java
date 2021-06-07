package com.damu.febs.server.test.controller;

import com.damu.febs.common.response.Result;
import com.damu.febs.common.response.ResultBuilder;
import com.damu.febs.server.test.data.dto.EmployeeDto;
import com.damu.febs.server.test.data.dto.TestLogDto;
import com.damu.febs.server.test.data.entity.Employee;
import com.damu.febs.server.test.data.entity.TestLog;
import com.damu.febs.server.test.service.EmployeeService;
import com.damu.febs.server.test.service.TestLogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("testLog")
public class TestLogController {

    @Resource
    private TestLogService testLogService;

    @PostMapping("/getStudentByCondition")
    public Result getStudentByCondition(@RequestBody TestLogDto testLogDto) {
        return ResultBuilder.success(testLogService.queryTestLogById(testLogDto.getId()));
    }

    @PostMapping("/addTestLog")
    public Result addEmployer(@RequestBody TestLogDto testLogDto) {
        return ResultBuilder.success(testLogService.addTestLog(new TestLog()));
    }
}
