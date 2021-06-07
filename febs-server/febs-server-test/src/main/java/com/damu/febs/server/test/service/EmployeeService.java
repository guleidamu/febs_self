package com.damu.febs.server.test.service;

import com.damu.febs.server.test.data.entity.Employee;

public interface EmployeeService {

    Employee queryEmployeeById(Integer id);

    int addEmployee(Employee employee);

    default int test(){
        return 3;
    }
}
