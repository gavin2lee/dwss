package com.gl.order.common.msg;

public interface CommonMessage {
	/*
	 * 
	 * systemId(2digits)+YYYYMMDD(8digits)+timestamp
	 */
	String getMessageId();
	

}
