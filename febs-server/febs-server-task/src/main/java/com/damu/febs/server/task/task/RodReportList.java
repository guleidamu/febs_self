package com.damu.febs.server.task.task;


import com.alibaba.fastjson.JSONObject;
import com.damu.febs.common.utils.DateUtil;
import com.damu.febs.server.task.data.dto.MailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class RodReportList {

//    @Resource
//    RodReportService rodReportService;

    public String dailyReportDownLoad(String params) {
        log.info("rod下载每日报表时间{}，参数为：{}", DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), params);
        MailDto mailDto = new MailDto();
        try {
            mailDto = JSONObject.parseObject(params,MailDto.class);
        } catch (Exception e) {
            log.error("dailyReportDownLoad每日报表下载定时参数{}出错，无法执行",params);
            e.printStackTrace();
        }
//        rodReportService.dailyReportDownLoad(mailDto);
        return null;
    }

    public String dailyReportLocalToServiceAndSendMail(String params) {
        log.info("rod推送每日报表时间{}，参数为：{}", DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), params);
        MailDto mailDto = new MailDto();
        try {
            mailDto = JSONObject.parseObject(params,MailDto.class);
        } catch (Exception e) {
            log.error("rod下载oBScrapWriteOffReportDownLoad定时参数{}出错，无法执行",params);
            e.printStackTrace();
        }
//        rodReportService.dailyReportLocalToServiceAndSendMail(mailDto);
        return null;
    }

    public String monthlyReportDownLoad(String params) {
        log.info("rod下载monthlyReport时间{}，参数为：{}", DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), params);
        MailDto mailDto = new MailDto();
        try {
            mailDto = JSONObject.parseObject(params,MailDto.class);
        } catch (Exception e) {
            log.error("rod下载monthlyReport定时参数{}出错，无法执行",params);
            e.printStackTrace();
        }
//        rodReportService.monthlyReportDownLoad(mailDto);
        return null;
    }

    public String monthlyReportSendMail(String params) {
        log.info("rod推送monthlyReportSendMail时间{}，参数为：{}", DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), params);
        MailDto mailDto = new MailDto();
        try {
            mailDto = JSONObject.parseObject(params,MailDto.class);
        } catch (Exception e) {
            log.error("rod下载oBScrapWriteOffReportDownLoad定时参数{}出错，无法执行",params);
            e.printStackTrace();
        }
//        rodReportService.monthlyReportSendMail(mailDto);
        return null;
    }

    public String oBScrapWriteOffReportDownLoad(String params) {
        log.info("rod下载oBScrapWriteOffReportDownLoad时间{}，参数为：{}", DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), params);
        MailDto mailDto = new MailDto();
        try {
            mailDto = JSONObject.parseObject(params,MailDto.class);
        } catch (Exception e) {
            log.error("rod下载oBScrapWriteOffReportDownLoad定时参数{}出错，无法执行",params);
            e.printStackTrace();
        }
//        rodReportService.oBScrapWriteOffReportDownLoad(mailDto);
        return null;
    }

    public String oBScrapWriteOffReportSendMail(String params) {
        log.info("rod推送oBScrapWriteOffReportSendMail时间{}，参数为：{}", DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), params);
        MailDto mailDto = new MailDto();
        try {
            mailDto = JSONObject.parseObject(params,MailDto.class);
        } catch (Exception e) {
            log.error("rod下载oBScrapWriteOffReportDownLoad定时参数{}出错，无法执行",params);
            e.printStackTrace();
        }
//        rodReportService.oBScrapWriteOffReportSendMail(mailDto);
        return null;
    }

    public String brdObSrListReportDownLoad(String params) {
        log.info("rod下载brdObSrListReportDownLoad时间{}，参数为：{}", DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), params);
        MailDto mailDto = new MailDto();
        try {
            mailDto = JSONObject.parseObject(params,MailDto.class);
        } catch (Exception e) {
            log.error("rod下载oBScrapWriteOffReportDownLoad定时参数{}出错，无法执行",params);
            e.printStackTrace();
        }
//        rodReportService.brdObSrListReportDownLoad(mailDto);
        return null;
    }

    public String brdObSrListReportLocalToServiceAndSendMail(String params) {
        log.info("rod推送brdObSrListReportLocalToServiceAndSendMail时间{}，参数为：{}", DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), params);
        MailDto mailDto = new MailDto();
        try {
            mailDto = JSONObject.parseObject(params,MailDto.class);
        } catch (Exception e) {
            log.error("rod下载oBScrapWriteOffReportDownLoad定时参数{}出错，无法执行",params);
            e.printStackTrace();
        }
//        rodReportService.brdObSrListReportLocalToServiceAndSendMail(mailDto);
        return null;
    }

    public void test(String params) {
        log.info("我是带参数的test方法，正在被执行，参数为：{}", params);
    }

    public void test1() {
        log.info("我是不带参数的test1方法，正在被执行");
    }

}
