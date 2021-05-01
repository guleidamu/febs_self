package com.damu.febs.server.test.service.impl;


import com.damu.febs.server.test.data.entity.Employee;
import com.damu.febs.server.test.mapper.EmployeeMapper;
import com.damu.febs.server.test.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmployeeImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;


    @Override
    public Employee queryEmployeeById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }
}
