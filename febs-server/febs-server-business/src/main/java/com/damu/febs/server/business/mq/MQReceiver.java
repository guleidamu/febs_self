package com.damu.febs.server.business.mq;


import com.damu.febs.server.business.config.RedisJedisService;
import com.damu.febs.server.business.data.entity.SeckillOrder;
import com.damu.febs.server.business.data.entity.TulingOrder;
import com.damu.febs.server.business.data.entity.User;
import com.damu.febs.server.business.data.message.SeckillMessage;
import com.damu.febs.server.business.data.vo.GoodsBo;
import com.damu.febs.server.business.mapper.TulingOrderMapper;
import com.damu.febs.server.business.service.OrderService;
import com.damu.febs.server.business.service.SeckillGoodsService;
import com.damu.febs.server.business.service.SeckillOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MQReceiver {

//    @Autowired
//    RedisService redisService;

    @Autowired
    RedisJedisService redisJedisService;

    @Autowired
    SeckillGoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Resource
    private TulingOrderMapper tulingOrderMapper;

    @Autowired
    SeckillOrderService seckillOrderService;

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        log.info("receive message:" + message);
        SeckillMessage seckillMessage = redisJedisService.stringToBean(message, SeckillMessage.class);
        User user = seckillMessage.getUser();
        long goodsId = seckillMessage.getGoodsId();
        GoodsBo goodsBo = goodsService.getseckillGoodsBoByGoodsId(goodsId);
        Integer stockCount = goodsBo.getStockCount();
        if(0 >= stockCount){
            return;
        }
        //判断是否已经秒杀到
        SeckillOrder seckillOrderByUserIdGoodsId = seckillOrderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if(seckillOrderByUserIdGoodsId != null){
            return;
        }
        //减库存，下订单，写入秒杀订单
        seckillOrderService.insert(user,goodsBo);
    }


    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE_MQ)
    public void receiveOnlyMq(String message) {
        log.info("receive message:" + message);
        SeckillMessage seckillMessage = redisJedisService.stringToBean(message, SeckillMessage.class);
        User user = seckillMessage.getUser();
        long goodsId = seckillMessage.getGoodsId();
        GoodsBo goodsBo = goodsService.getseckillGoodsBoByGoodsId(goodsId);
        Integer stockCount = goodsBo.getStockCount();
        if(0 >= stockCount){
            log.info("已没有库存。。。。。。。");
            return;
        }
//        //判断是否已经秒杀到
//        SeckillOrder seckillOrderByUserIdGoodsId = seckillOrderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
//        if(seckillOrderByUserIdGoodsId != null){
//            return;
//        }
        //减库存，下订单，写入秒杀订单
        seckillOrderService.insert(user,goodsBo);
    }

    @RabbitListener(queues = MQConfig.TULING_MIAOSHA_QUEUE_MQ)
    public void tuLingSecKill(String message) {

        log.info("新增数据receive message:" + message);
        TulingOrder tulingOrder = redisJedisService.stringToBean(message, TulingOrder.class);
//        log.info("新增数据:" + tulingOrder);
        tulingOrderMapper.insert(tulingOrder);
    }

}
