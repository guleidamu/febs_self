package com.damu.febs.server.business.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tuling_order")
public class TulingOrder {

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("goods_id")
    private Long goodsId;

    @TableField("user_id")
    private Integer userId;

    @TableField("order_id")
    private Integer orderId;

    @TableField("remain")
    private Integer remain;

    @TableField("create_time")
    private Date createTime;

    @TableField("port")
    private String port;

}
