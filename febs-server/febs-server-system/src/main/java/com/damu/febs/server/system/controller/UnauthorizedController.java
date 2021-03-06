package com.damu.febs.server.system.controller;

import com.damu.febs.common.response.Result;
import com.damu.febs.common.response.ResultBuilder;
import com.damu.febs.server.system.data.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("unauthorized")
@Slf4j
public class UnauthorizedController {

    @PostMapping("/resultData")
    public Result resultData(@RequestBody Map student) {
//        String id = (String) student.get("id");
        return ResultBuilder.success("未验证接口调用成功");
    }
}
