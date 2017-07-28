package com.gl.order.common.dict;

public enum MessageType {
    Default(0), NormalReq(1), NormalResp(2), HeartBeatReq(3), HeartBeatResp(4);
    
    private int code;
    
    private MessageType(int code){
        this.code = code;
    }
    
    public int getCode(){
        return code;
    }
}
