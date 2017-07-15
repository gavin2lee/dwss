package com.gl.order.common.msg;

public interface CommonMessage {
    /*
     * 
     * systemId(2digits)+YYYYMMDD(8digits)+timestamp
     */
    String getMessageId();

    /*
     * 0-default,1-nomal req, 2-normal resp,3-heartbeat req,4-heartbeat resp
     */
    int getMessageType();

}
