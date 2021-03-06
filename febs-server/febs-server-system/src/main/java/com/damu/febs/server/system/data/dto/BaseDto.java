package com.damu.febs.server.system.data.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto implements Serializable {

    @ApiModelProperty("页码")
    public Integer pageNum = 1;

    @ApiModelProperty("页大小")
    public Integer pageSize = 10;
}
