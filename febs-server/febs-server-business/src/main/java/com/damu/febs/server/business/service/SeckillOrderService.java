package com.damu.febs.server.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.damu.febs.server.business.data.entity.OrderInfo;
import com.damu.febs.server.business.data.entity.SeckillOrder;
import com.damu.febs.server.business.data.entity.User;
import com.damu.febs.server.business.data.vo.GoodsBo;


public interface SeckillOrderService extends IService<SeckillOrder> {
    SeckillOrder getSeckillOrderByUserIdGoodsId(long userId, long goodsId);

    OrderInfo insert(User user, GoodsBo goodsBo);
}
