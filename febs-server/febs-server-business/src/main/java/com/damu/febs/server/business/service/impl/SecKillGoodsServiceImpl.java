package com.damu.febs.server.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.damu.febs.server.business.data.entity.SeckillGoods;
import com.damu.febs.server.business.data.vo.GoodsBo;
import com.damu.febs.server.business.mapper.GoodsMapper;
import com.damu.febs.server.business.mapper.SeckillGoodsMapper;
import com.damu.febs.server.business.service.SeckillGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
//@Service("SecKillGoodsServiceImpl")
@Service
public class SecKillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements SeckillGoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsBo> getSeckillGoodsList() {
        return goodsMapper.selectAllGoodes();
    }

    @Override
    public GoodsBo getseckillGoodsBoByGoodsId(long goodsId) {
        return goodsMapper.getseckillGoodsBoByGoodsId(goodsId);
    }

    @Override
    public int reduceStock(long goodsId) {
        return goodsMapper.updateStock(goodsId);
    }
}
