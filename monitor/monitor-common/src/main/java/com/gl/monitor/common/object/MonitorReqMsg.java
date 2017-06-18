package com.gl.monitor.common.object;

public class MonitorReqMsg {
	private CommonMsgHeader header;
	private CommonRequest body;
	public CommonMsgHeader getHeader() {
		return header;
	}
	public void setHeader(CommonMsgHeader header) {
		this.header = header;
	}
	public CommonRequest getBody() {
		return body;
	}
	public void setBody(CommonRequest body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "MonitorReqMsg [header=" + header + ", body=" + body + "]";
	}
	
	
}
