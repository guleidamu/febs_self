//package com.damu.febs.server.test.mq.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//
//@Service
//@Slf4j
//public class ConfirmProvider implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//    @PostConstruct
//    public void init() {
//        rabbitTemplate.setReturnCallback(this);
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setMandatory(true);
//        log.info("进入初始化init>>>>>>");
//    }
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        if(ack){
//            System.out.println("确认了这条消息："+correlationData + "cause:" + cause);
//        }else{
//            System.out.println("确认失败了："+correlationData+"；出现异常："+cause);
//        }
//    }
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.out.println("这条消息发送失败了"+message+",请处理");
//    }
//    public void publisMessage(String message){
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.convertAndSend("javatrip",message);
//    }
//}
//
//
////import org.springframework.amqp.core.Message;
////import org.springframework.amqp.rabbit.connection.ConnectionFactory;
////import org.springframework.amqp.rabbit.connection.CorrelationData;
////import org.springframework.amqp.rabbit.core.RabbitTemplate;
////import org.springframework.context.annotation.Bean;
////import org.springframework.stereotype.Service;
////
////@Service
////public class ConfirmProvider{
////    @Bean
////    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
////        RabbitTemplate rabbitTemplate = new RabbitTemplate();
////        rabbitTemplate.setConnectionFactory(connectionFactory);
////        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
////        rabbitTemplate.setMandatory(true);
////
////        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
////            @Override
////            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
////                System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
////                System.out.println("ConfirmCallback:     " + "确认情况：" + ack);
////                System.out.println("ConfirmCallback:     " + "原因：" + cause);
////            }
////        });
////        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
////            @Override
////            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
////                System.out.println("ReturnCallback:     " + "消息：" + message);
////                System.out.println("ReturnCallback:     " + "回应码：" + replyCode);
////                System.out.println("ReturnCallback:     " + "回应信息：" + replyText);
////                System.out.println("ReturnCallback:     " + "交换机：" + exchange);
////                System.out.println("ReturnCallback:     " + "路由键：" + routingKey);
////            }
////        });
////        return rabbitTemplate;
////    }
////}
//
//
