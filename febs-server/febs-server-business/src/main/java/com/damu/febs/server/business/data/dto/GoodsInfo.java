package com.damu.febs.server.business.data.dto;

import lombok.Data;

@Data
public class GoodsInfo {
    private Long goodsId;
    private String path;
    //秒杀先携带userKey作为key进去查询，查询用户
    private String userKey;

    private Long userId;
}
