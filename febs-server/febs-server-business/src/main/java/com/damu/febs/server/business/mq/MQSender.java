package com.damu.febs.server.business.mq;

import com.damu.febs.common.service.RedisService;
import com.damu.febs.server.business.data.entity.TulingOrder;
import com.damu.febs.server.business.data.message.SeckillMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSender {
    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate ;

    public void sendSeckillMessage(SeckillMessage mm) {
        String msg = RedisService.beanToString(mm);
        log.info("send message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE, msg);
    }

    public void sendSeckillMessageOnlyMQ(SeckillMessage mm) {
        String msg = RedisService.beanToString(mm);
        log.info("send message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE_MQ, msg);
    }

    public void sendSeckillMessageTuLing(TulingOrder tulingOrder) {
        String msg = RedisService.beanToString(tulingOrder);
        log.info("send message TuLing:"+msg);
        amqpTemplate.convertAndSend(MQConfig.TULING_MIAOSHA_QUEUE_MQ, msg);
    }
}
