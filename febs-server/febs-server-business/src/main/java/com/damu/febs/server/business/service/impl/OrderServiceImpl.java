package com.damu.febs.server.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.damu.febs.server.business.data.entity.OrderInfo;
import com.damu.febs.server.business.mapper.OrderInfoMapper;
import com.damu.febs.server.business.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderService {
    @Override
    public long addOrder(OrderInfo orderInfo) {
        return this.baseMapper.insert(orderInfo);
    }

    @Override
    public OrderInfo getOrderInfo(long rderId) {
        return this.baseMapper.selectById(rderId);
    }
}
