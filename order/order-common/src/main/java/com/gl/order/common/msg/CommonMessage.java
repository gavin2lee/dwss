package com.gl.order.common.msg;

import com.gl.order.common.dict.MessageType;

public interface CommonMessage {
    int DEFAULT_MSG = MessageType.Default.getCode();
    int NORMAL_REQ_MSG = MessageType.NormalReq.getCode();
    int NORMAL_RESP_MSG = MessageType.NormalResp.getCode();
    int HEARTBEAT_REQ_MSG = MessageType.HeartBeatReq.getCode();
    int HEARTBEAT_RESP_MSG = MessageType.HeartBeatResp.getCode();

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
