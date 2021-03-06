package com.damu.febs.server.system.controller;

import com.damu.febs.common.response.Result;
import com.damu.febs.common.response.ResultBuilder;
import com.damu.febs.server.system.data.dto.StudentDto;
import com.damu.febs.server.system.data.entity.Student;
import com.damu.febs.server.system.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("student")
@Slf4j
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/getStudentById")
    public Result<Student> getStudentById(@RequestBody Map student) {
        String id = (String) student.get("id");
        Student studentResult = studentService.getStudentById(id);
//        Result<Student> result = new Result<>();
//        return result.setData("d");

        return ResultBuilder.success(studentResult);
    }

    @PostMapping("/getStudentByCondition")
    public Result getStudentByCondition(@RequestBody StudentDto studentDto) {

//        studentService.getStudentByCondition(studentDto);
//        Result<Student> result = new Result<>();
//        return result.setData("d");

        return ResultBuilder.success(studentService.getStudentByCondition(studentDto));
    }

    @PostMapping("/saveStudentBatch")
    public Result saveStudentBatch(@RequestBody Map map) {
        log.info(map.toString());
        String name = (String) map.get("name");
        String remark = (String) map.get("remark");
        String number = (String) map.get("number");
        int num = Integer.parseInt(number);

        Date date = new Date();
        List<Student> studentList = new ArrayList<>();
        int i;

        for (i = 0; i < num; i++) {
            Student student1 = new Student(name, 26, "男", date, "美丽大山里" + i, remark);
            studentList.add(student1);
        }
        boolean b = studentService.saveBatch(studentList);
        Result result = new Result();
        result.setData(i);
        result.setSuccess(b);
        return result;
    }

    @PostMapping("/addStudentBatch")
    public Result addStudentBatch(@RequestBody Map map) {
        log.info(map.toString() + "ganni");
        String name = (String) map.get("name");
        String remark = (String) map.get("remark");
        String number = (String) map.get("number");
        int num = Integer.parseInt(number);
        Date date = new Date();
//        List<Student> studentList = new ArrayList<>();
        int i;
        for (i = 0; i < num; i++) {
            Student student1 = new Student(name, 26, "男", date, "美丽大山里" + i, remark);
            studentService.save(student1);
//            studentList.add(student1);
        }
        log.info("插入" + i);
        return ResultBuilder.success(i);
//        boolean b = studentService.saveBatch(studentList);
//        Result result = new Result();
//        result.setData(i);
//        result.setSuccess(b);
//        return result;
    }


}
