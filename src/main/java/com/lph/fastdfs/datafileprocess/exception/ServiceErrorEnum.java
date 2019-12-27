package com.lph.fastdfs.datafileprocess.exception;

public enum ServiceErrorEnum {
    E_Connection_HttpRequestException("S10001","http请求错误!"),
    ; 
    
    private String errorCode;
    
    private String message;
    
    ServiceErrorEnum(String code,String message){
        this.errorCode=code;
        this.message=message;
    }

    public String getMessage(){
        return"[code="+errorCode+",message="+message+"]";
    }
    public String getErrorCode() {
    
        return errorCode;
    }
    
    public String getMsg(){
        return message;
    }
    
    
    
}
  