package com.gl.fsm.core.state;

import com.gl.fsm.core.StateContext;

public class CancelledState extends State {

	public CancelledState() {
		super("S5", "CancelledState");
	}

	@Override
	protected void internalProcess(StateContext ctx) {

	}

}
