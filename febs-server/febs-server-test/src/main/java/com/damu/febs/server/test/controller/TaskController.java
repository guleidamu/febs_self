package com.damu.febs.server.test.controller;

import com.damu.febs.common.response.Result;
import com.damu.febs.common.response.ResultBuilder;
import com.damu.febs.server.test.data.dto.MailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("task")
public class TaskController {



    @PostMapping("/getMailDtoInfo")
    public Result getMailDtoInfo(@RequestBody MailDto mailDto) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("通过其他服务调用的接口" + mailDto);
        return ResultBuilder.success("getMailDtoInfo接口调用成功");
    }


    @PostMapping("/getMailDtoInfo2")
    public Result getMailDtoInfo2(@RequestBody MailDto mailDto) {
        log.info("通过其他服务调用的接口getMailDtoInfo2" + mailDto);
        return ResultBuilder.success("getMailDtoInfo2接口调用成功");
    }


}
