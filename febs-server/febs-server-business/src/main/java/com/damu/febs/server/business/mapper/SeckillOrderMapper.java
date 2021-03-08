package com.damu.febs.server.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.damu.febs.server.business.data.entity.SeckillOrder;


public interface SeckillOrderMapper extends BaseMapper<SeckillOrder> {
    int insertSelective(SeckillOrder record);
}
