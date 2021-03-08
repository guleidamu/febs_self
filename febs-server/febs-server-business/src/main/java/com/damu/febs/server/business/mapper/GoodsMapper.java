package com.damu.febs.server.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.damu.febs.server.business.data.entity.Goods;
import com.damu.febs.server.business.data.vo.GoodsBo;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsBo> selectAllGoodes();

    GoodsBo getseckillGoodsBoByGoodsId(long goodsId);


    int updateStock(long goodsId);
}
