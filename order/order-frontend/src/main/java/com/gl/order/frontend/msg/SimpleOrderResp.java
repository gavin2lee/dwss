package com.gl.order.frontend.msg;

import com.gl.order.common.msg.BaseResponse;

public class SimpleOrderResp extends BaseResponse {

    @Override
    public String toString() {
        return "SimpleOrderResp [messageId=" + messageId + ", serverId=" + serverId + ", responseTime=" + responseTime
                + ", retCode=" + retCode + ", retMsg=" + retMsg + ", messageType=" + messageType + "]";
    }

}
