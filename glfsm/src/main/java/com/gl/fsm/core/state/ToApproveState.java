package com.gl.fsm.core.state;

import com.gl.fsm.core.StateContext;

public class ToApproveState extends State {

	public ToApproveState() {
		this("S0", "ToApprove");
	}

	public ToApproveState(String stateCode, String stateName) {
		super(stateCode, stateName);
	}

	@Override
	protected void internalProcess(StateContext ctx) {
		// TODO Auto-generated method stub

	}

}
