package com.gl.fsm2.core.state;

import com.gl.fsm2.core.StateContext;

public class CancelledState extends State {

	public CancelledState() {
		super("S5", "CancelledState");
	}

	@Override
	protected void internalProcess(StateContext ctx) {

	}

}
