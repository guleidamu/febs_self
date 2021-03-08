package com.damu.febs.server.business.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("seckill_goods")
public class SeckillGoods {
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("goods_id")
    private Long goodsId;

    @TableField("seckil_price")
    private BigDecimal seckilPrice;

    @TableField("stock_count")
    private Integer stockCount;

    @TableField("start_date")
    private Date startDate;

    @TableField("end_date")
    private Date endDate;

}