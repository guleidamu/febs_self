//package com.damu.febs.server.test.mq;
//
//
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
//public class RabbitMqConnectionUtils {
//
//    /**
//     * 获取MQ的连接
//     */
//    public static Connection getConnection() throws IOException, TimeoutException{
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("8.140.152.182");
//        connectionFactory.setPort(5672);
//        connectionFactory.setUsername("meili");
//        connectionFactory.setPassword("meili");
//        return connectionFactory.newConnection();
//    }
//}
