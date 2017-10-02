package com.gl.fsm.core.state;

import com.gl.fsm.core.StateContext;

public class ToAdvancedApproveState extends State {

	public ToAdvancedApproveState() {
		this("S1", "ToAdvancedApprove");
		// TODO Auto-generated constructor stub
	}

	public ToAdvancedApproveState(String stateCode, String stateName) {
		super(stateCode, stateName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void internalProcess(StateContext ctx) {
		// TODO Auto-generated method stub

	}

}
