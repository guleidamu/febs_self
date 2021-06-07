package com.damu.febs.server.test.mq.RabbitMqReceive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class RabbitMqReceiver {

//    @RabbitListener(queues = "TestDirectQueue")
//    public void TestDirectQueueReceive1(Map message) {
//        log.info("TestDirectQueue消费者收到消息  : " + message.toString());
//    }

    @RabbitListener(queues = "topic.man")
    public void topicReceive1(Map message) {
        log.info("topic.man消费者收到消息  : " + message.toString());
    }

    @RabbitListener(queues = "topic.woman")
    public void topicReceive2(Map message) {
        log.info("topic.woman消费者收到消息  : " + message.toString());
    }

    @RabbitListener(queues = "fanout.A")
    public void process(Map testMessage) {
        log.info("fanout.A消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "fanout.B")
    public void fanoutB(Map testMessage) {
        log.info("fanout.B消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "fanout.C")
    public void fanoutC(Map testMessage) {
        log.info("fanout.C消费者收到消息  : " + testMessage.toString());
    }

}
