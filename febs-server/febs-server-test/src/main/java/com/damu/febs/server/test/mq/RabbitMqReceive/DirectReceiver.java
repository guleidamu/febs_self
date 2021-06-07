package com.damu.febs.server.test.mq.RabbitMqReceive;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class DirectReceiver {

    @RabbitListener(queues = "TestDirectQueue")
    public void receive(String msg, Channel channel, Message message) throws IOException {
        try {
            log.info("DirectReceiver消费者收到消息  : " + msg);
            //手动ACK
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.info("mq，消费者异常");
            e.printStackTrace();
        }
    }
}
