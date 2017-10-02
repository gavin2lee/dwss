package com.gl.fsm.core.state;

public interface StateDescription {
	String PSEDU = "S00";
	String PSEDU_NAME = "PseduState";
	
	String TO_APPROVE = "S0";
	String TO_APPROVE_NAME = "ToApproveState";
	
	String TO_L2_APPROVE = "S1";
	String TO_L2_APPROVE_NAME = "ToL2ApproveState";
	
	String APPROVED = "S2";
	String APPROVED_NAME = "ApprovedState";
	
	String RESUMED = "S3";
	String RESUMED_NAME = "ResumedState";
	
	String CLOSED = "S4";
	String CLOSED_NAME = "ClosedState";
	
	String CANCELLED = "S5";
	String CANCELLED_NAME = "CancelledState";
	
	String REJECTED = "S6";
	String REJECTED_NAME = "RejectedState";
	
	String PRE_APPROVED = "S7";
	String PRE_APPROVED_NAME = "PreApprovedState";
}
