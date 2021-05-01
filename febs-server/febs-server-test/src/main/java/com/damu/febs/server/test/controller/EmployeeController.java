package com.damu.febs.server.test.controller;

import com.damu.febs.common.response.Result;
import com.damu.febs.common.response.ResultBuilder;
import com.damu.febs.server.test.data.dto.EmployeeDto;
import com.damu.febs.server.test.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @PostMapping("/getStudentByCondition")
    public Result getStudentByCondition(@RequestBody EmployeeDto employeeDto) {
        return ResultBuilder.success(employeeService.queryEmployeeById(employeeDto.getId()));
    }
}
