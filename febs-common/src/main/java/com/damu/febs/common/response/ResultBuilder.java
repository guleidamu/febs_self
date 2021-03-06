package com.damu.febs.common.response;

import java.io.Serializable;

public class ResultBuilder {
    private ResultBuilder(){
    }

//    public static <T extends Serializable> Result<T> success(T data){
public static <T extends Serializable> Result<T> success(T data){

    Result<T> result=new Result();
        result.setCode("200");
        result.setData(data);
        return result;
    }

    public static Result success(){
        Result result=new Result();
        return result;
    }

    public static Result error(String code,String message){
        Result result=new Result();
        result.setCode(code);
        result.setSuccess(false);
        result.setDescription(message);
        return result;
    }

    public static Result error(ResultCode resultCode){
        Result result=new Result();
        result.setCode(resultCode.getCode());
        result.setSuccess(false);
        result.setDescription(resultCode.getDescription());
        return result;
    }

    public static Result error(IResultCode code,String message){
        Result result=new Result();
        result.setSuccess(false);
        result.setCode(code.code());
        result.setDescription(message);
        return result;
    }
}
