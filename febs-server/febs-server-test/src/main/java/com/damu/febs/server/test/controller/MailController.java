package com.damu.febs.server.test.controller;

import com.damu.febs.common.response.Result;
import com.damu.febs.common.response.ResultBuilder;
import com.damu.febs.server.test.data.dto.MailDto;
import com.damu.febs.server.test.service.MailTestComponent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("mail")
public class MailController {

    @Resource
    private MailTestComponent mailTestComponent;

    @PostMapping("/sendMail")
    public Result addEmployer(@RequestBody MailDto mailDto)throws Exception {
        mailTestComponent.sendEmailMesssage(mailDto);
        return ResultBuilder.success("发送成功");
    }
}
