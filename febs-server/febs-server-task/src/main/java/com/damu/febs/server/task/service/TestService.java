package com.damu.febs.server.task.service;

import com.damu.febs.common.response.Result;
import com.damu.febs.server.task.config.FeignConfig;
import com.damu.febs.server.task.data.dto.MailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Component("TestService")
//@Component
@FeignClient(value = "FEBS-Server-Test", configuration = FeignConfig.class ,fallback = SchedualServiceHystric.class)
public interface TestService {

    @RequestMapping(value = "/task/getMailDtoInfo",method = RequestMethod.POST)
    Result sendMail(MailDto mailDto);

    @RequestMapping(value = "/task/getMailDtoInfo2",method = RequestMethod.POST)
    Result sendMail2(MailDto mailDto);
}
