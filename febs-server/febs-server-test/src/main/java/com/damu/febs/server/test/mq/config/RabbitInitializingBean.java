//package com.damu.febs.server.test.mq.config;
//
//
//import lombok.AllArgsConstructor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@AllArgsConstructor
//public class RabbitInitializingBean implements InitializingBean {
//
//    private RabbitTemplate rabbitTemplate;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        rabbitTemplate.setConfirmCallback(new ProviderConfirmCallback());
//        rabbitTemplate.setReturnCallback(new ProviderReturnCallback());
//    }
//}
