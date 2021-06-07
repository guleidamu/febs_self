package com.damu.febs.server.test.service.impl;


import com.damu.febs.server.test.data.entity.Employee;
import com.damu.febs.server.test.mapper.EmployeeMapper;
import com.damu.febs.server.test.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class EmployeeImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;


    @Override
    public Employee queryEmployeeById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addEmployee(Employee employee) {
        employee.setName("新生活，新概念");
        employee.setAge(26);
        employee.setDate(new Date());
        employee.setFlag("1");
        employee.setSex(1);
        employeeMapper.insert(employee);
        return 0;
    }
}
