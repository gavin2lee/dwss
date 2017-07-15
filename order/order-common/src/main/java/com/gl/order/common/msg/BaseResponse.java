package com.gl.order.common.msg;

public abstract class BaseResponse implements CommonResponse {
    protected String messageId;
    protected String serverId;
    protected String responseTime;
    protected String retCode;
    protected String retMsg;
    protected int messageType;

    @Override
    public String getMessageId() {
        return messageId;
    }

    @Override
    public String getServerId() {
        return serverId;
    }

    @Override
    public String getResponseTime() {
        return responseTime;
    }

    @Override
    public String getRetCode() {
        return retCode;
    }

    @Override
    public String getRetMsg() {
        return retMsg;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "BaseResponse [messageId=" + messageId + ", serverId=" + serverId + ", responseTime=" + responseTime
                + ", retCode=" + retCode + ", retMsg=" + retMsg + ", messageType=" + messageType + "]";
    }

}
