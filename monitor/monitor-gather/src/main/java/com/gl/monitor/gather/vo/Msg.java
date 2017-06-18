package com.gl.monitor.gather.vo;

public class Msg {
	private MsgHeader header;
	private ComputerInfo body;

	public MsgHeader getHeader() {
		return header;
	}

	public void setHeader(MsgHeader header) {
		this.header = header;
	}

	public ComputerInfo getBody() {
		return body;
	}

	public void setBody(ComputerInfo body) {
		this.body = body;
	}

}
