package com.gl.fsm2.core.state;

import static com.gl.fsm2.core.state.StateDescription.*;

import com.gl.fsm2.core.StateContext;

public class ApprovedState extends State {

	public ApprovedState() {
		super(APPROVED, APPROVED_NAME);
	}

	@Override
	protected void internalProcess(StateContext ctx) {
		// TODO Auto-generated method stub

	}

}
