package com.damu.febs.server.system.controller;

import com.damu.febs.common.response.Result;
import com.damu.febs.common.response.ResultBuilder;
import com.damu.febs.server.system.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("async")
@Slf4j
public class AsyncController {

    @Resource
    private AsyncService asyncService;

    @PostMapping("/async")
    public Result getStudentById(@RequestBody Map student) {
        asyncService.getThread();

        return ResultBuilder.success("success");
    }
}
