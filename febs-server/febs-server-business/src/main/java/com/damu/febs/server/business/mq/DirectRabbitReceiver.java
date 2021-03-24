package com.damu.febs.server.business.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class DirectRabbitReceiver {

    @RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
    public void process(Map testMessage){
        log.info("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }
}
