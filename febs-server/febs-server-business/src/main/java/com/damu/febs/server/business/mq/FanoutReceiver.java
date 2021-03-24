package com.damu.febs.server.business.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FanoutReceiver {

    //    @RabbitHandler
    @RabbitListener(queues = "fanout.A")//监听的队列名称 fanout.A
    public void process(Map testMessage) {
        System.out.println("fanout.A消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "fanout.B")
    public void B(Map testMessage) {
        System.out.println("fanout.B消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "fanout.C")
    public void C(Map testMessage) {
        System.out.println("fanout.C消费者收到消息  : " + testMessage.toString());
    }
}
