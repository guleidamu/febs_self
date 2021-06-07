package com.damu.febs.server.test.mq.RabbitMqReceive;

//import com.damu.febs.server.test.mq.RabbitMqConnectionUtils;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;


@Service
@Slf4j
public class MqReceive1 {

//    @Autowired
//    RabbitTemplate rabbitTemplate ;

    private static final String QUEUE_NAME = "test_simple_queue";

    @RabbitListener(queues = QUEUE_NAME)
    public void receive(String message) {
        try {
            log.info("receive message:" + message);
            //减库存，下订单，写入秒杀订单
        } catch (Exception e) {
            log.info("mq，消费者异常");
            e.printStackTrace();
        }
    }

//    public void sendMessage(Map map) {
//        rabbitTemplate.convertAndSend(map);
//    }

//    public static void main(String[] args) throws IOException,TimeoutException {
//        Connection connection = RabbitMqConnectionUtils.getConnection();
//
//        //获取通道
//        Channel channel = connection.createChannel();
//
//        //创建队列声明
//        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//
//        //消费者
//        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String msg = new String(body, "utf-8");
//                System.out.println("接收数据"+msg);
//            }
//        };
//        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);
//    }


}
