package com.damu.febs.server.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.damu.febs.server.business.data.entity.TulingOrder;
import com.damu.febs.server.business.mapper.TulingOrderMapper;
import com.damu.febs.server.business.service.TulingOrderService;
import org.springframework.stereotype.Service;

@Service
public class TulingOrderServiceImpl extends ServiceImpl<TulingOrderMapper, TulingOrder> implements TulingOrderService {

}
