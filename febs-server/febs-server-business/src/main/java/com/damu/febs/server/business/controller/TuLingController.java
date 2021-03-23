package com.damu.febs.server.business.controller;


import com.damu.febs.common.entity.CurrentUser;
import com.damu.febs.common.service.RedisService;
import com.damu.febs.common.utils.FebsUtil;
import com.damu.febs.server.business.data.entity.SeckillGoods;
import com.damu.febs.server.business.data.entity.TulingOrder;
import com.damu.febs.server.business.data.message.SeckillMessage;
import com.damu.febs.server.business.mapper.SeckillGoodsMapper;
import com.damu.febs.server.business.mapper.TulingOrderMapper;
import com.damu.febs.server.business.mq.MQSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.management.StringValueExp;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    @Autowired
    RedisService redisService;

    @Value("${server.port}")
    private String port;

    @Transactional
    @GetMapping("/deductStock")
    public String deductStock() {
        CurrentUser currentUser = new CurrentUser();
        try {
            currentUser = FebsUtil.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String lockKey = "lockKey";
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "damu",6000, TimeUnit.MILLISECONDS);
        if (!result) {
            return "拥挤中，请稍等一会重试";
        }
        String stockString = (String) redisService.get("stock");
        Integer stock = Integer.parseInt(stockString);
        if (stock > 0) {
            int realStock = stock - 1;
            String s = realStock + "";
//            stringRedisTemplate.opsForValue().set("stock", s);
            redisService.set("stock", realStock + "");
            log.info("扣减库存成功，剩余库存：" + realStock);
            SeckillGoods seckillGoods = new SeckillGoods();
            seckillGoods.setId(5L);
            seckillGoods.setStockCount(realStock);
            seckillGoodsMapper.updateById(seckillGoods);
            TulingOrder tulingOrder = new TulingOrder();
            tulingOrder.setUserId(currentUser.getUserId());
            tulingOrder.setGoodsId(5L);
            tulingOrder.setRemain(realStock);
            tulingOrder.setPort(port);
            tulingOrder.setCreateTime(new Date());
            mqSender.sendSeckillMessageTuLing(tulingOrder);
            log.info("库存stock:{}", stock);
            log.info("库存realStock:{}", realStock);
            stringRedisTemplate.delete(lockKey);
        } else {
            log.info("库存不足，扣减库存失败");
        }
        return "请求完毕";
    }

    @Transactional
    @GetMapping("/deductStockMq")
    public String deductStockMq() {
        CurrentUser currentUser = new CurrentUser();
        try {
            currentUser = FebsUtil.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String lockKey = "lockKey";
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "damu",6000, TimeUnit.MILLISECONDS);
        if (!result) {
            return "拥挤中，请稍等一会重试";
        }
        String stockString = (String) redisService.get("stock");
        Integer stock = Integer.parseInt(stockString);
        if (stock > 0) {
            int realStock = stock - 1;
            redisService.set("stock", realStock + "");
            log.info("扣减库存成功，剩余库存：" + realStock);
            TulingOrder tulingOrder = new TulingOrder();
            tulingOrder.setUserId(currentUser.getUserId());
            tulingOrder.setGoodsId(5L);
            tulingOrder.setRemain(realStock);
            tulingOrder.setPort(port);
            tulingOrder.setCreateTime(new Date());
            mqSender.sendSeckillMessageTuLing(tulingOrder);
            log.info("库存stock:{}", stock);
            stringRedisTemplate.delete(lockKey);
        } else {
            log.info("库存不足，扣减库存失败");
        }
        return "请求完毕";
    }


    @Transactional
    @GetMapping("/deductStockSynchronized")
    public String deductStockynchronized() {
        /**
         * synchronized是java中解决并发问题的一种最常用方法，也是最简单的一种。
         * 能够保证同一时刻只有一个线程执行该段代码，保证程序的并发安全性
         * synchronized如果代码发生错误，jvm会释放锁
         *  synchronized的作用主要有三
         * 1、确保线程互斥的访问同步代码
         * 2、保证共享变量的修改能够及时可见
         * 3、有效解决重排序问题
         */
        CurrentUser currentUser = new CurrentUser();
        try {
            currentUser = FebsUtil.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        synchronized (this) {
            String stockString = (String) redisService.get("stock");
            if (StringUtils.isEmpty(stockString)) {
                return "没有库存数据，请核查";
            }
            int stock = Integer.parseInt(stockString);
            if (stock > 0) {
                int realStock = stock - 1;
                redisService.set("stock", realStock + "");
                log.info("扣减库存成功，剩余库存：" + realStock);
                SeckillGoods seckillGoods = new SeckillGoods();
                seckillGoods.setId(5L);
                seckillGoods.setStockCount(realStock);
                seckillGoodsMapper.updateById(seckillGoods);
                TulingOrder tulingOrder = new TulingOrder();
                tulingOrder.setUserId(currentUser == null ? null : currentUser.getUserId());
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

    Lock lock = new ReentrantLock();

    @Transactional
    @GetMapping("/deductStockLock")
    public String deductStockLock() {
        lock.lock();
        log.info("get lock" + lock.hashCode());
        CurrentUser currentUser = new CurrentUser();
        //reentrantLock上锁期间，如果代码发生错误，不会释放锁，容易造成死锁。所以finally进行释放锁
        try {
            currentUser = FebsUtil.getCurrentUser();
            String stockString = (String) redisService.get("stock");
            if (StringUtils.isEmpty(stockString)) {
                return "没有库存数据，请核查";
            }
            int stock = Integer.parseInt(stockString);
            if (stock > 0) {
                int realStock = stock - 1;
                redisService.set("stock", realStock + "");
                log.info("扣减库存成功，,,剩余库存：" + realStock);
                SeckillGoods seckillGoods = new SeckillGoods();
                seckillGoods.setId(5L);
                seckillGoods.setStockCount(realStock);
                seckillGoodsMapper.updateById(seckillGoods);
                TulingOrder tulingOrder = new TulingOrder();
                tulingOrder.setUserId(currentUser == null ? null : currentUser.getUserId());
                tulingOrder.setGoodsId(5L);
                tulingOrder.setRemain(realStock);
                tulingOrder.setPort(port);
                tulingOrder.setCreateTime(new Date());
                tulingOrderMapper.insert(tulingOrder);
            } else {
                log.info("库存不足，扣减库存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("下一步就执行finally");
            lock.unlock();
        }
        return "请求完毕";
    }


}
