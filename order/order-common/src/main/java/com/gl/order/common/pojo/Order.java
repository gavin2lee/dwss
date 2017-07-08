package com.gl.order.common.pojo;

public interface Order {
	/*
	 * YYYYMMDD(8digits)+timestamp
	 */
	String getOrderId();
	
	/*
	 * YYYYMMDDHHmmssSSS
	 */
	String getCreateTime();
	
	/*
	 * YYYYMMDDHHmmssSSS
	 */
	String getUpdateTime();
	
	/*
	 * 
	 * original systemId(2digits)+YYYYMMDD(8digits)+timestamp
	 */
	String getBizId();
}
