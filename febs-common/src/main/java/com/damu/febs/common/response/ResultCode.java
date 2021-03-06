package com.damu.febs.common.response;

public enum ResultCode implements IResultCode {
    SUCCESS("200","成功"),
    PARAM_ERROR("400","参数错误"),
    UNAUTHORIZED("401","未授权"),
    INTERNAL_ERROR("500","系统内部错误"),
    SOURCE_NOT_FOUND("404","资源为找到"),
    REPEAT_SECONDKILL("10001","重复秒杀"),
    USER_NOTFOUND("10002","查找不到用户"),
    USER_NO_LOGIN("10005","用户未登录"),
    ERROR_PASSWORD("10003", "密码错误")
    ;
    ResultCode(String code,String description){
        this.code=code;
        this.description=description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String code;

    private String description;

    @Override
    public String code() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    public static ResultCode fromValue(String code){
        ResultCode[] arr$=values();
        int len$=arr$.length;

        for(int i$=0;i$<len$;++i$){
            ResultCode c=arr$[i$];
            if(c.code.equals(code)){
                return c;
            }
        }
        throw new RuntimeException("unknow result code"+code);
    }
}
