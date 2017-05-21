package com.gl.monitor.common.object;

public class CommonMsg {
	private CommonMsgHeader header;
	private CommonMsgBody body;

	public CommonMsg(CommonMsgHeader header, CommonMsgBody body) {
		super();
		this.header = header;
		this.body = body;
	}

	public CommonMsgHeader getHeader() {
		return header;
	}

	public CommonMsgBody getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "CommonMsg [header=" + header + ", body=" + body + "]";
	}
}
