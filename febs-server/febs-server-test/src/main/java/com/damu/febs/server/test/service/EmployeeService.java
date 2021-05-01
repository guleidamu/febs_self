package com.damu.febs.server.test.service;

import com.damu.febs.server.test.data.entity.Employee;

public interface EmployeeService {

    Employee queryEmployeeById(Integer id);
}
