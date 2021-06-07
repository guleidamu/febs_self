package com.damu.febs.server.test.mq.RabbitMqSend;

//import com.damu.febs.server.test.mq.RabbitMqConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Service
public class MqSend1 {

    @Autowired
    AmqpTemplate amqpTemplate ;

    @Autowired
    RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME = "test_simple_queue";

    public void sendMessage(String message) {
        amqpTemplate.convertAndSend(QUEUE_NAME, message);
//        rabbitTemplate.convertAndSend(map);
    }

//    public static void main(String[] args) throws IOException,TimeoutException{
//        Connection connection = RabbitMqConnectionUtils.getConnection();
//
//        //获取通道
//        Channel channel = connection.createChannel();
//
//        //创建队列声明
//        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//
//        //发送消息
//        String msg = "hello simple";
//        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
//        System.out.println("send msg");
//
//        //关闭连接
//        channel.close();
//        connection.clearBlockedListeners();
//    }
}
