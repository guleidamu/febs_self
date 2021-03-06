package com.damu.febs.common.response;

import java.text.MessageFormat;

public class BusinessException extends RuntimeException{
    private static final long seriaVersionUID=1L;
    private final String code;
    private final String businessMessage;

    public BusinessException(String code,String businessMessage){
        super(getMessage(code,businessMessage));
        this.code=code;
        this.businessMessage=businessMessage;
    }

    public BusinessException(Result result){
        super(getMessage(result.getCode(),result.getDescription()));
        this.code=result.getCode();
        this.businessMessage=result.getDescription();
    }

    private static String getMessage(String code,String businessMessage){
        return MessageFormat.format(code,businessMessage);
    }

    public String getCode(){return code;}
    public String getBusinessMessage(){return this.businessMessage;}
    public Throwable fillInStackTrace(){return  this;}
}
