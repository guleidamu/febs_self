package com.damu.febs.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "响应结果代码",required = true)
    private String code;

    @ApiModelProperty(value = "响应结果描述",required = true)
    private String description;

    @ApiModelProperty(value="成功标识",required = true)
    private boolean success;

    @ApiModelProperty(value = "响应结果数据",required = true)
    private T data;

    public Result(T data){
        super();
        this.data = data;
    }

    public Result(){
        this.code=ResultCode.SUCCESS.code();
        this.description=ResultCode.SUCCESS.description();
        this.success=true;
    }
}
