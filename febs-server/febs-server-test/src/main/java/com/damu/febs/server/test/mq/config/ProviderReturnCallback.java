//package com.damu.febs.server.test.mq.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//
//@Slf4j
//@Service
//public class ProviderReturnCallback implements RabbitTemplate.ReturnCallback{
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//    @PostConstruct
//    public void init() {
//        rabbitTemplate.setReturnCallback(this);
////        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setMandatory(true);
////        log.info("进入初始化init>>>>>>");
//    }
//
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.out.println("这条消息发送失败了"+message+",请处理");
//    }
//    public void publisMessage(String message){
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.convertAndSend("javatrip",message);
//    }
//}
