package com.damu.febs.server.business.controller;


import com.damu.febs.server.business.data.entity.SeckillGoods;
import com.damu.febs.server.business.data.entity.TulingOrder;
import com.damu.febs.server.business.mapper.SeckillGoodsMapper;
import com.damu.febs.server.business.mapper.TulingOrderMapper;
import com.damu.febs.server.business.mq.MQSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("second")
@Slf4j
public class TuLingController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SeckillGoodsMapper seckillGoodsMapper;

    @Resource
    private TulingOrderMapper tulingOrderMapper;

    @Autowired
    MQSender mqSender;

    @Value("${server.port}")
    private String port;

    @Transactional
    @GetMapping("/deductStock")
    public String deductStock() {

        String lockKey = "lockKey";
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "damu");
        if (!result) {
            return "拥挤中，请稍等一会重试";
        }

        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
        if (stock > 0) {
            int realStock = stock - 1;
            stringRedisTemplate.opsForValue().set("stock", realStock + "");
            log.info("扣减库存成功，剩余库存：" + realStock);
            SeckillGoods seckillGoods = new SeckillGoods();
            seckillGoods.setId(5L);
            seckillGoods.setStockCount(realStock);
            seckillGoodsMapper.updateById(seckillGoods);

            TulingOrder tulingOrder = new TulingOrder();
            tulingOrder.setGoodsId(5L);
//            tulingOrder.setOrderId();
            tulingOrder.setRemain(realStock);
            tulingOrder.setPort(port);
            tulingOrder.setCreateTime(new Date());
            mqSender.sendSeckillMessageTuLing(tulingOrder);
//            tulingOrderMapper.insert(tulingOrder);

            log.info("库存stock:{}", stock);
            log.info("库存realStock:{}", realStock);
            stringRedisTemplate.delete(lockKey);
        } else {
            log.info("库存不足，扣减库存失败");
        }
        return "请求完毕";
    }

    @Transactional
    @GetMapping("/deductStockJvm")
    public String deductStockJvm() {
        /**
         * synchronized是java中解决并发问题的一种最常用方法，也是最简单的一种。
         * 能够保证同一时刻只有一个线程执行该段代码，保证程序的并发安全性
         *
         *  synchronized的作用主要有三
         * 1、确保线程互斥的访问同步代码
         * 2、保证共享变量的修改能够及时可见
         * 3、有效解决重排序问题
         */

        synchronized (this) {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                log.info("扣减库存成功，剩余库存：" + realStock);
                SeckillGoods seckillGoods = new SeckillGoods();
                seckillGoods.setId(5L);
                seckillGoods.setStockCount(realStock);
                seckillGoodsMapper.updateById(seckillGoods);
                TulingOrder tulingOrder = new TulingOrder();
                tulingOrder.setGoodsId(5L);
                tulingOrder.setRemain(realStock);
                tulingOrder.setPort(port);
                tulingOrder.setCreateTime(new Date());
                tulingOrderMapper.insert(tulingOrder);
            } else {
                log.info("库存不足，扣减库存失败");
            }
        }
        return "请求完毕";
    }


}
