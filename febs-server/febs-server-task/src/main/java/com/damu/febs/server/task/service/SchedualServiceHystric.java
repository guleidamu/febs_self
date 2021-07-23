package com.damu.febs.server.task.service;

import com.damu.febs.common.response.Result;
import com.damu.febs.server.task.data.dto.MailDto;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHystric implements TestService {
    @Override
    public Result sendMail(MailDto mailDto) {
        return new Result("SchedualServiceHystric里面的sendMail");
    }

    @Override
    public Result sendMail2(MailDto mailDto) {
        return new Result("SchedualServiceHystric里面的sendMail2");
    }
}
