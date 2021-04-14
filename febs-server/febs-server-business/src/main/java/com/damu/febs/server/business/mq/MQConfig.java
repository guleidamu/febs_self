package com.damu.febs.server.business.mq;

//import com.rabbitmq.client.ConnectionFactory;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Configuration
public class MQConfig {

    //    @Autowired
//    RabbitAdmin rabbitAdmin;
//
    public static final String MIAOSHA_QUEUE = "seckill.queue";
    public static final String MIAOSHA_QUEUE_MQ = "seckill.queue.mq";
    public static final String TULING_MIAOSHA_QUEUE_MQ = "seckill.queue.tuLing";
    public static final String SIMPLE_QUEUE = "simple.queue";
    public static final String DEAD_QUEUE = "dead_queue";
    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    public static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";
    public static final String ROUTINGKEY_EMAIL = "inform.#.email.#";
    public static final String EXCHANGE = "exchange";
    //以下为死信队列

    private static final String DEAD_EXCHANGE = "x-dead-letter-exchange";

    //声明交换机
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
    }

    //声明QUEUE_INFORM_EMAIL队列，配置死信队列需要的参数
    @Bean(QUEUE_INFORM_EMAIL)
    public Queue QUEUE_INFORM_EMAIL() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key", "dev");
        return new Queue(QUEUE_INFORM_EMAIL, true, false, false, map);
    }

    //    ROUTINGKEY_EMAIL队列绑定交换机，指定routingKey
    @Bean
    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_EMAIL).noargs();
    }

    //TULING_MIAOSHA_QUEUE_MQ，配置死信队列需要的参数
    @Bean(TULING_MIAOSHA_QUEUE_MQ)
    public Queue TULING_MIAOSHA_QUEUE_MQ() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key", "dev");
        return new Queue(TULING_MIAOSHA_QUEUE_MQ, true, false, false, map);
    }

    //    ROUTINGKEY_EMAIL队列绑定交换机，指定routingKey
    @Bean
    public Binding BINDING_TULING_MIAOSHA_QUEUE_MQ(@Qualifier(TULING_MIAOSHA_QUEUE_MQ) Queue queue,
                                                   @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_EMAIL).noargs();
    }


    @Bean(DEAD_EXCHANGE)
    public Exchange dead_exchange() {
        return ExchangeBuilder.directExchange(DEAD_EXCHANGE).durable(true).build();
    }

    @Bean(DEAD_QUEUE)
    public Queue dead_routing_key() {
        return QueueBuilder.durable("dead_queue").build();
    }


    @Bean(SIMPLE_QUEUE)
    public Queue simple_queue() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key", "dev");
        return new Queue(SIMPLE_QUEUE, true, false, false, map);
    }

    //    ROUTINGKEY_EMAIL队列绑定交换机，指定routingKey
    @Bean
    public Binding BINDING_SIMPLE_QUEUE(@Qualifier(SIMPLE_QUEUE) Queue queue,
                                        @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_EMAIL).noargs();
    }


    @Bean("dead_bind")
    public Binding dead_bind(@Qualifier("dead_queue") Queue queue, @Qualifier(DEAD_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("dev").noargs();
    }

}