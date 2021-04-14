package com.damu.febs.server.business.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.damu.febs.common.service.RedisService;
import com.damu.febs.server.business.data.entity.TulingOrder;
import com.damu.febs.server.business.data.message.SeckillMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MQSender {
    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate ;

    @Autowired
    RabbitTemplate rabbitTemplate ;

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
//        amqpTemplate.convertAndSend(MQConfig.TULING_MIAOSHA_QUEUE_MQ, msg);
        amqpTemplate.convertAndSend(MQConfig.TULING_MIAOSHA_QUEUE_MQ, msg);
    }

    public void sendSimpleQueue(String message) {
        amqpTemplate.convertAndSend(MQConfig.SIMPLE_QUEUE, message);
    }


    /**
     * 设置了消息持久化
     */
    public void sendToUploadMsg(TulingOrder obj, String routingKey) {

        try {
            String jsonString = JSON.toJSONString(obj);
            TulingOrder tulingOrder = JSONObject.parseObject(jsonString, TulingOrder.class);
            rabbitTemplate.convertAndSend(MQConfig.SIMPLE_QUEUE,jsonString, message -> {
                //设置该条消息持久化
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            }, new CorrelationData(UUID.randomUUID().toString()));
        } catch (Exception e) {
            log.info(routingKey + "发送消息异常!");
        }
    }

}
