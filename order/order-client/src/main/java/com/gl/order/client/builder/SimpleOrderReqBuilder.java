package com.gl.order.client.builder;

import com.gl.order.client.msg.SimpleOrderReq;
import com.gl.order.client.pojo.SimpleOrder;

public class SimpleOrderReqBuilder {
	/*
	 * 
	 * systemId(2digits)+YYYYMMDD(8digits)+timestamp
	 */
	private String messageId;
	private String clientId;
	private String requestTime;
	private SimpleOrder order;

	public SimpleOrderReqBuilder(String messageId) {
		this.messageId = messageId;
	}

	public SimpleOrderReqBuilder withMessageId(String messageId) {
		this.messageId = messageId;
		return this;
	}

	public SimpleOrderReqBuilder withClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	public SimpleOrderReqBuilder withRequestTime(String requestTime) {
		this.requestTime = requestTime;
		return this;
	}

	public SimpleOrderReqBuilder withOrder(SimpleOrder order) {
		this.order = order;
		return this;
	}

	public SimpleOrderReq build() {
		SimpleOrderReq req = new SimpleOrderReq();

		req.setClientId(clientId);
		req.setMessageId(messageId);
		req.setOrder(order);
		req.setRequestTime(requestTime);

		return req;
	}
}
