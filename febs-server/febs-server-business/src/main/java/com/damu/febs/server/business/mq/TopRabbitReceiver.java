package com.damu.febs.server.business.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TopRabbitReceiver {

//    @RabbitHandler
    @RabbitListener(queues = "topic.woman")//监听的队列名称 topic.woman
    public void process(Map testMessage) {
        System.out.println("TopicTotalReceiver消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "topic.man")//监听的队列名称 topic.woman
    public void topMan(Map testMessage) {
        System.out.println("TopicManReceiver消费者收到消息  : " + testMessage.toString());
    }



}
