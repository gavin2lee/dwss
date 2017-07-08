package com.gl.order.common.msg;

public abstract class BaseRequest implements CommonRequest {
	protected String messageId;
	protected String clientId;
	protected String requestTime;

	@Override
	public String getMessageId() {
		return messageId;
	}

	@Override
	public String getClientId() {
		return clientId;
	}

	@Override
	public String getRequestTime() {
		return requestTime;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	@Override
	public String toString() {
		return "BaseRequest [messageId=" + messageId + ", clientId=" + clientId + ", requestTime=" + requestTime + "]";
	}

}
