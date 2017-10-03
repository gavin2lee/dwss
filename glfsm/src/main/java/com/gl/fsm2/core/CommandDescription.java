package com.gl.fsm2.core;

public interface CommandDescription {
	String SUBMIT = "901";//EMP
	String CANCEL = "902";//EMP
	String RESUME = "903";//EMP
	
	String CLOSE = "801";//SYS
	
	String APPROVE = "101";//MNGT
	String REJECT = "102";//MNGT
	
	String SUBMIT_NAME = "提交";//EMP
	String CANCEL_NAME = "取消";//EMP
	String RESUME_NAME = "销假";//EMP
	
	String CLOSE_NAME = "关闭";//SYS
	
	String APPROVE_NAME = "批准";//MNGT
	String REJECT_NAME = "拒绝";//MNGT
	
}
