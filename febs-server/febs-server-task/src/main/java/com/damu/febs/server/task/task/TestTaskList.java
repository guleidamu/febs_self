package com.damu.febs.server.task.task;

import com.damu.febs.common.response.Result;
import com.damu.febs.server.task.data.dto.MailDto;
import com.damu.febs.server.task.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class TestTaskList {

    @Resource
    TestService testService;

    public void sendMail(String params) {
        log.info("我是sendMail方法，正在被执行，参数为：{}", params);
        MailDto mailDto = new MailDto();
        mailDto.setReportDay(params);
        testService.sendMail(mailDto);
    }

    public void sendMail2(String params) {
        log.info("我是sendMail2方法，正在被执行，参数为：{}", params);
        MailDto mailDto = new MailDto();
        mailDto.setReportDay(params);
        Result result = testService.sendMail2(mailDto);
    }
}
