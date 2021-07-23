package com.damu.febs.server.task.controller;

import com.damu.febs.common.response.Result;
import com.damu.febs.server.task.data.dto.MailDto;
import com.damu.febs.server.task.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("task")
@Slf4j
@RequiredArgsConstructor
public class TaskController {

    @Resource
    TestService testService;

    @PostMapping("/test")
    public String test(@RequestBody MailDto mailDto) {
        return mailDto.getReportDay();
    }

    @GetMapping("/test1")
    public String test1() {
        return "succe56ss";
    }

    @GetMapping("/testService")
    public Result testService() {
        MailDto mailDto = new MailDto();
        mailDto.setReportDay(new Date().toString());
        return testService.sendMail(mailDto);
    }

    @GetMapping("/testService2")
    public Result testService2() {
        MailDto mailDto = new MailDto();
        mailDto.setReportDay(new Date().toString());
        return testService.sendMail2(mailDto);
    }

}
