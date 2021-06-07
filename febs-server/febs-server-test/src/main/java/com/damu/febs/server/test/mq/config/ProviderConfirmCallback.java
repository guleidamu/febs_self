//package com.damu.febs.server.test.mq.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//@Slf4j
//public class ProviderConfirmCallback implements RabbitTemplate.ConfirmCallback {
//
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//    @PostConstruct
//    public void init() {
////        rabbitTemplate.setReturnCallback(this);
//        rabbitTemplate.setConfirmCallback(this);
////        rabbitTemplate.setMandatory(true);
////        log.info("进入初始化init>>>>>>");
//    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        if(ack){
//            System.out.println("确认了这条消息："+correlationData + "cause:" + cause);
//        }else{
//            System.out.println("确认失败了："+correlationData+"；出现异常："+cause);
//        }
//    }
//}
