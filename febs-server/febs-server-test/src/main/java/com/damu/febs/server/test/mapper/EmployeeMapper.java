package com.damu.febs.server.test.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.damu.febs.server.test.data.entity.Employee;

@DS("base")
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}