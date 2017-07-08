package com.gl.order.common.msg;

public interface CommonResponse extends CommonMessage{
	/*
	 * S+3digits,i.e,S001,S002
	 */
	String getServerId();
	
	/*
	 * The time when this object was created
	 * YYYYMMDDHHmmssSSS
	 */
	String getResponseTime();
	
	/*
	 * 4digits,i.e,0000,1000
	 */
	String getRetCode();
	
	
	/*
	 * message prompt returned
	 */
	String getRetMsg();
}
