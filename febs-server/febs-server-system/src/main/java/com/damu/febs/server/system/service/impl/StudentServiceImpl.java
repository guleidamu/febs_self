package com.damu.febs.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.damu.febs.server.system.data.dto.StudentDto;
import com.damu.febs.server.system.data.entity.Student;
import com.damu.febs.server.system.mapper.StudentMapper;
import com.damu.febs.server.system.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public IPage<Student> getStudentByCondition(StudentDto studentDto) {
        Long beginTime = new Date().getTime();
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = Wrappers.lambdaQuery();
        studentLambdaQueryWrapper.eq(Student::getName,studentDto.getName());
        Page<Student> page = new Page<>(studentDto.getPageNum(), studentDto.getPageSize());
        IPage<Student> studentIPage = this.baseMapper.selectPage(page, studentLambdaQueryWrapper);
        return studentIPage;
    }
}
