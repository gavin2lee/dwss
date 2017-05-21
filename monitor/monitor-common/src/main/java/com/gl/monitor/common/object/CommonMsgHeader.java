package com.gl.monitor.common.object;

public class CommonMsgHeader {
	private String msgId;
	private String srcSysId;
	private String targetSysId;
	private int msgType;
	private String msgState;
	private String retCode;
	private String retMsg;
	
	private String securityContext;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getSrcSysId() {
		return srcSysId;
	}

	public void setSrcSysId(String srcSysId) {
		this.srcSysId = srcSysId;
	}

	public String getTargetSysId() {
		return targetSysId;
	}

	public void setTargetSysId(String targetSysId) {
		this.targetSysId = targetSysId;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public String getMsgState() {
		return msgState;
	}

	public void setMsgState(String msgState) {
		this.msgState = msgState;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getSecurityContext() {
		return securityContext;
	}

	public void setSecurityContext(String context) {
		this.securityContext = context;
	}

	@Override
	public String toString() {
		return "CommonMsgHeader [msgId=" + msgId + ", srcSysId=" + srcSysId + ", targetSysId=" + targetSysId
				+ ", msgType=" + msgType + ", msgState=" + msgState + ", retCode=" + retCode + ", retMsg=" + retMsg
				+ ", securityContext=" + securityContext + "]";
	}
	
}
