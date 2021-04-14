package com.damu.febs.server.business.mq;

import com.alibaba.fastjson.JSONObject;
import com.damu.febs.server.business.config.RedisJedisService;
//import com.damu.febs.server.business.config.RedisService;
import com.damu.febs.server.business.data.entity.SeckillGoods;
import com.damu.febs.server.business.data.entity.SeckillOrder;
import com.damu.febs.server.business.data.entity.TulingOrder;
import com.damu.febs.server.business.data.entity.User;
import com.damu.febs.server.business.data.message.SeckillMessage;
import com.damu.febs.server.business.data.vo.GoodsBo;
import com.damu.febs.server.business.mapper.SeckillGoodsMapper;
import com.damu.febs.server.business.mapper.TulingOrderMapper;
import com.damu.febs.server.business.service.OrderService;
import com.damu.febs.server.business.service.SeckillGoodsService;
import com.damu.febs.server.business.service.SeckillOrderService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class MQReceiver {

    @Autowired
    RedisJedisService redisJedisService;

    @Autowired
    SeckillGoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Resource
    private SeckillGoodsMapper seckillGoodsMapper;

    @Resource
    private TulingOrderMapper tulingOrderMapper;

    @Autowired
    SeckillOrderService seckillOrderService;

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        try {
            log.info("receive message:" + message);
            SeckillMessage seckillMessage = redisJedisService.stringToBean(message, SeckillMessage.class);
            User user = seckillMessage.getUser();
            long goodsId = seckillMessage.getGoodsId();
            GoodsBo goodsBo = goodsService.getseckillGoodsBoByGoodsId(goodsId);
            Integer stockCount = goodsBo.getStockCount();
            if (0 >= stockCount) {
                return;
            }
            //判断是否已经秒杀到
            SeckillOrder seckillOrderByUserIdGoodsId = seckillOrderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
            if (seckillOrderByUserIdGoodsId != null) {
                return;
            }
            //减库存，下订单，写入秒杀订单
            seckillOrderService.insert(user, goodsBo);
        } catch (Exception e) {
            log.info("mq，消费者异常");
            e.printStackTrace();
        }
    }


    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE_MQ)
    public void receiveOnlyMq(String message) {
        log.info("receive message:" + message);
        SeckillMessage seckillMessage = redisJedisService.stringToBean(message, SeckillMessage.class);
        User user = seckillMessage.getUser();
        long goodsId = seckillMessage.getGoodsId();
        GoodsBo goodsBo = goodsService.getseckillGoodsBoByGoodsId(goodsId);
        Integer stockCount = goodsBo.getStockCount();
        if (0 >= stockCount) {
            log.info("已没有库存。。。。。。。");
            return;
        }
        seckillOrderService.insert(user, goodsBo);
    }

    //默认情况下是自动签收
    //设置死信队列，当达到条件，就将队列推送到死信队列中，确保消息不丢失
    //将队列设置手动签收
    //消费者catch代码，catch，basicReject拒绝，并把requeue设置为false.
    //finally，消息签收，否则消息被消费，还是unacked，导致每次启动服务都会重新消费这些数据
    //    @RabbitListener(queues = MQConfig.TULING_MIAOSHA_QUEUE_MQ)
    @RabbitListener(queues = MQConfig.TULING_MIAOSHA_QUEUE_MQ)
    public void tuLingSecKill(Channel channel, String message, Message msg, @Headers Map<String, Object> headers) throws IOException {
        try {
            log.info("新增数据receive message:" + message);
            int a = 2 / 0;
            TulingOrder tulingOrder = redisJedisService.stringToBean(message, TulingOrder.class);
            Integer realStock = tulingOrder.getRemain();
            SeckillGoods seckillGoods = new SeckillGoods();
            seckillGoods.setId(5L);
            seckillGoods.setStockCount(realStock);
            seckillGoodsMapper.updateById(seckillGoods);
            tulingOrderMapper.insert(tulingOrder);
        } catch (Exception e) {
            log.info("mq，消费者异常,message信息" + message);
            e.printStackTrace();
            //手动应答，采取拒绝，第二位参数requeue，必须设置为false
            channel.basicReject(msg.getMessageProperties().getDeliveryTag(), false);
            System.out.println("解决了");
            //下面的抛异常就随意了，因为上面已经把当前的消息扔到队列外，所以不会无限执行该条消息，也就是说，抛异常只会抛一次，并不会无限下去。
            throw new RuntimeException("还是不行？？");
        } finally {
            // 消息签收
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        }
    }


//    @RabbitListener(queues = MQConfig.DEAD_QUEUE)
//    public void DEADEXCHANGE(Channel channel, String message,Message msg, @Headers Map<String, Object> headers) throws IOException {
//        try {
//            log.info("死信队列消费信息" + message);
////            int a = 2 / 0;
//            TulingOrder tulingOrder = redisJedisService.stringToBean(message, TulingOrder.class);
//            Integer realStock = tulingOrder.getRemain();
//            SeckillGoods seckillGoods = new SeckillGoods();
//            seckillGoods.setId(5L);
//            seckillGoods.setStockCount(realStock);
//            seckillGoodsMapper.updateById(seckillGoods);
//            tulingOrderMapper.insert(tulingOrder);
//        } catch (Exception e) {
//            log.info("mq，消费者异常,message信息" + message);
//            e.printStackTrace();
//            //手动应答，采取拒绝，第二位参数requeue，必须设置为false
//            channel.basicReject(msg.getMessageProperties().getDeliveryTag(),false);
//            //下面的抛异常就随意了，因为上面已经把当前的消息扔到队列外，所以不会无限执行该条消息，也就是说，抛异常只会抛一次，并不会无限下去。
//            throw new RuntimeException("死信队列消费信息，还是不行？？");
//        }finally {
//            // 消息签收
//            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG),false);
//        }
//    }

    @RabbitListener(queues = MQConfig.SIMPLE_QUEUE)
    public void handlerMq(String msg, Channel channel, Message message) throws IOException {
        try {
            log.info(MQConfig.SIMPLE_QUEUE + "消息" + msg);
            //业务处理代码
            TulingOrder tulingOrder = JSONObject.parseObject(msg, TulingOrder.class);
            log.info(MQConfig.SIMPLE_QUEUE + "消息转换成实体类" + tulingOrder);
            //手动ACK
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.error("消息已重复处理失败,拒绝再次接收...", e);
                //方法拒绝deliveryTag对应的消息，第二个参数是否requeue，true则重新入队列(重新消费)，否则丢弃或者进入死信队列。
                // 该方法reject后，该消费者还是会消费到该条被reject的消息。
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
//                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 拒绝消息

            } else {
                log.error("消息即将再次返回队列处理...", e);
                //basic.nack方法为不确认deliveryTag对应的消息，第二个参数是否应用于多消息，第三个参数是否requeue，
                // 与basic.reject区别就是同时支持多个消息，可以nack该消费者先前接收未ack的所有消息。nack后的消息也会被自己消费到。
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    @RabbitListener(queues = MQConfig.DEAD_QUEUE)
    public void deadQueue(String msg, Channel channel, Message message) throws IOException {
        try {
            log.info(MQConfig.DEAD_QUEUE + "消息" + msg);
            //业务处理代码
//            TulingOrder tulingOrder = JSONObject.parseObject(msg, TulingOrder.class);
//            log.info(MQConfig.SIMPLE_QUEUE + "消息转换成实体类" + tulingOrder);
            //手动ACK
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
//            if (message.getMessageProperties().getRedelivered()) {
//                log.error("消息已重复处理失败,拒绝再次接收...", e);
//                //方法拒绝deliveryTag对应的消息，第二个参数是否requeue，true则重新入队列(重新消费)，否则丢弃或者进入死信队列。
//                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
////                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 拒绝消息
//
//            } else {
//                log.error("消息即将再次返回队列处理...", e);
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//            }
        }
    }

}
