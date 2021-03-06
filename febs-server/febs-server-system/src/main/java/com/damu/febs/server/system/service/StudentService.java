package com.damu.febs.server.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.damu.febs.server.system.data.dto.StudentDto;
import com.damu.febs.server.system.data.entity.Student;

import java.util.Date;
import java.util.Map;

public interface StudentService extends IService<Student> {

    Student getStudentById(String id);

    int insertStudentByBatch(Student student);

    IPage<Student> getStudentByCondition(StudentDto studentDto);

    int saveStudentByBatch(Map map);


}
