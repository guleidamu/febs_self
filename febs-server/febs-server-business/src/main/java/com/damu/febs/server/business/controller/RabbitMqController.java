package com.damu.febs.server.business.controller;

import com.damu.febs.common.entity.CurrentUser;
import com.damu.febs.common.service.RedisService;
import com.damu.febs.common.utils.FebsUtil;
import com.damu.febs.server.business.data.entity.TulingOrder;
import com.damu.febs.server.business.mapper.SeckillGoodsMapper;
import com.damu.febs.server.business.mapper.TulingOrderMapper;
import com.damu.febs.server.business.mq.MQSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("rabbitMq")
@Slf4j
public class RabbitMqController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    MQSender mqSender;

    @Autowired
    RedisService redisService;

    @Value("${server.port}")
    private String port;

    @Transactional
    @GetMapping("/simpleQueue")
    public String simpleQueue() {
        String message = "调试生产者发送消息";
        mqSender.sendSimpleQueue(message);
        return message;
    }

    @GetMapping("/messageDersistence")
    public String messageDersistence() {
        String message = "调试生产者发送消息";
        TulingOrder tulingOrder = new TulingOrder();
        tulingOrder.setUserId(897L);
        tulingOrder.setGoodsId(5L);
        tulingOrder.setRemain(666);
        tulingOrder.setPort(port);
        tulingOrder.setCreateTime(new Date());
        mqSender.sendToUploadMsg(tulingOrder,"");
        return message;
    }


}
