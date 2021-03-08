package com.damu.febs.server.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.damu.febs.server.business.data.entity.OrderInfo;
import com.damu.febs.server.business.data.entity.SeckillOrder;
import com.damu.febs.server.business.data.entity.User;
import com.damu.febs.server.business.data.vo.GoodsBo;
import com.damu.febs.server.business.mapper.SeckillOrderMapper;
import com.damu.febs.server.business.service.OrderService;
import com.damu.febs.server.business.service.SeckillGoodsService;
import com.damu.febs.server.business.service.SeckillOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {

    @Resource
    private SeckillOrderMapper seckillOrderMapper;
    @Autowired
    SeckillGoodsService seckillGoodsService;
    @Autowired
    OrderService orderService;

    @Override
    public SeckillOrder getSeckillOrderByUserIdGoodsId(long userId, long goodsId) {
        QueryWrapper<SeckillOrder> seckillOrderQueryWrapper = new QueryWrapper<>();
        seckillOrderQueryWrapper.eq("user_id", userId);
        seckillOrderQueryWrapper.eq("goods_id", goodsId);
        return seckillOrderMapper.selectOne(seckillOrderQueryWrapper);
    }

    @Override
    @Transactional
    public OrderInfo insert(User user, GoodsBo goodsBo) {
        int success = seckillGoodsService.reduceStock(goodsBo.getId());
        if (1 == success) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setCreateDate(new Date());
            orderInfo.setAddrId(0L);
            orderInfo.setGoodsCount(1);
            orderInfo.setGoodsId(goodsBo.getId());
            orderInfo.setGoodsName(goodsBo.getGoodsName());
            orderInfo.setGoodsPrice(goodsBo.getSeckillPrice());
            orderInfo.setOrderChannel(1);
            orderInfo.setStatus(0);
            orderInfo.setUserId((long) user.getId());
            //添加信息进入订单信息
            long orderId = orderService.addOrder(orderInfo);
            log.info("orderId -->" +orderId+"");
            SeckillOrder seckillOrder = new SeckillOrder();
            seckillOrder.setGoodsId(goodsBo.getId());
            seckillOrder.setOrderId(orderInfo.getId());
            seckillOrder.setUserId((long)user.getId());
            //插入秒杀表
            seckillOrderMapper.insert(seckillOrder);
//            seckillOrderMapper.insertSelective(seckillOrder);
            return orderInfo;
        }
        return null;
    }
}
