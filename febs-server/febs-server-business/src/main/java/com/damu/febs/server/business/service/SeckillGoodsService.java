package com.damu.febs.server.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.damu.febs.server.business.data.entity.SeckillGoods;
import com.damu.febs.server.business.data.vo.GoodsBo;


import java.util.List;

//import com.baomidou.mybatisplus.extension.service.IService;

public interface SeckillGoodsService extends IService<SeckillGoods> {


    List<GoodsBo> getSeckillGoodsList();

    GoodsBo getseckillGoodsBoByGoodsId(long goodsId);

    int reduceStock(long goodsId);
}
