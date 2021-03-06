package com.damu.febs.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("请求响应结果信息")
public class ResponseVo<T> {

    @ApiModelProperty("响应结果代码")
    private String code;

    @ApiModelProperty("响应结果描述")
    private String description;

    @ApiModelProperty("响应结果数据")
    private T data;
}
