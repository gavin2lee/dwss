package com.gl.monitor.common.object;


public class CommonMsgBuilder {
	public static final String DEF_SYS_ID = "000000";
	public static final String DEF_TO_SYS_ID = "000001";
	public static final String DEF_MSG_STATE = "0";
	public static final int DEF_MSG_TYPE = CommonMsgType.NOMAL.ordinal();
	public static final String DEF_RET_CODE = "0000";
	public static final String DEF_RET_MSG = "";
	
	private static final String SYS_ID;
	
	static{
		SYS_ID = System.getProperty("sysId", DEF_SYS_ID);
	}
	
	public static CommonMsg buildDefMsg(){
		CommonMsg defMsg = new CommonMsg(buildDefHeader(), null);
		return defMsg;
	}
	
	public static CommonMsg buildMsg(MonitorReqMsg inMsg){
		CommonMsg outMsg = buildDefMsg();
		outMsg.getHeader().setTargetSysId(inMsg.getHeader().getSrcSysId());
		outMsg.getHeader().setSrcSysId(SYS_ID);
		
		return outMsg;
	}
	
	public static CommonMsg buildReqMsgTemplate(){
		CommonMsgHeader header = buildDefHeader();
		CommonMsgBody body = new CommonRequest();
		
		return new CommonMsg(header, body);
	}
	
	private static CommonMsgHeader buildDefHeader(){
		CommonMsgHeader header = new CommonMsgHeader();
		header.setMsgId(String.valueOf(System.nanoTime()));
		header.setMsgState(DEF_MSG_STATE);
		header.setMsgType(DEF_MSG_TYPE);
		header.setSrcSysId(DEF_SYS_ID);
		header.setTargetSysId(DEF_TO_SYS_ID);
		header.setRetCode(DEF_RET_CODE);
		header.setRetMsg(DEF_RET_MSG);
		
		return header;
	}
	
}
