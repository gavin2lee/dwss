package com.gl.fsm2.core.state;

import com.gl.fsm2.core.StateContext;

public class ToL2ApproveState extends State {

	public ToL2ApproveState() {
		this(StateDescription.TO_L2_APPROVE, StateDescription.TO_L2_APPROVE_NAME);
	}

	public ToL2ApproveState(String stateCode, String stateName) {
		super(stateCode, stateName);
	}

	@Override
	protected void internalProcess(StateContext ctx) {

	}

}
