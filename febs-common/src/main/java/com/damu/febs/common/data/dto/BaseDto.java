package com.damu.febs.common.data.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto implements Serializable {

    /**
     * 当前页面数据量
     */
    @TableField(exist=false)
    private int pageSize = 10;
    /**
     * 当前页码
     */
    @TableField(exist=false)
    private int pageNum = 1;
}
