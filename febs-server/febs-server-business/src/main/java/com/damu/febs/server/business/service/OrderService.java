package com.damu.febs.server.business.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.damu.febs.server.business.data.entity.OrderInfo;


public interface OrderService extends IService<OrderInfo> {
    long addOrder(OrderInfo orderInfo);

    OrderInfo getOrderInfo(long rderId);
}
