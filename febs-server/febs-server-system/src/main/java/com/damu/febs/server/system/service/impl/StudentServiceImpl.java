package com.damu.febs.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.damu.febs.common.response.Result;
import com.damu.febs.server.system.data.dto.StudentDto;
import com.damu.febs.server.system.data.entity.Student;
import com.damu.febs.server.system.mapper.StudentMapper;
import com.damu.febs.server.system.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    public Student getStudentById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public int insertStudentByBatch(Student student) {
        String name = student.getName();
        String remark = student.getRemark();
        Date date = new Date();
        for (int i = 0; i < 1000; i++){
//            this.baseMapper.insert()
        }
//        this.in
            return 0;
    }

//    insertStudentByBatch

    @Override
    public IPage<Student> getStudentByCondition(StudentDto studentDto) {
        Long beginTime = new Date().getTime();
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = Wrappers.lambdaQuery();
        studentLambdaQueryWrapper.eq(Student::getName,studentDto.getName());
        Page<Student> page = new Page<>(studentDto.getPageNum(), studentDto.getPageSize());
        IPage<Student> studentIPage = this.baseMapper.selectPage(page, studentLambdaQueryWrapper);
        return studentIPage;
    }

    @Override
    public int saveStudentByBatch(Map map) {
        String name = (String) map.get("name");
        String remark = (String) map.get("remark");
        String address = (String) map.get("address");
        String sex = (String) map.get("sex");
        int age = Integer.parseInt((String)map.get("age"));

        String number = (String) map.get("number");
        int num = Integer.parseInt(number);

        Date date = new Date();
        List<Student> studentList = new ArrayList<>();
        int i;

        for (i = 0; i < num; i++) {
            Student student1 = new Student(name, age, sex, date, address + i, remark);
            studentList.add(student1);
        }
        boolean b = this.saveBatch(studentList);
        Result result = new Result();
        result.setData(i);
        result.setSuccess(b);
        return 0;
    }
}
