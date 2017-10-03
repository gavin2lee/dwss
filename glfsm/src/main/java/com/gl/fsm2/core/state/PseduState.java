package com.gl.fsm2.core.state;

import com.gl.fsm2.core.StateContext;

public class PseduState extends State {

	public PseduState() {
		super(StateDescription.PSEDU, StateDescription.PSEDU_NAME);
	}

	@Override
	protected void internalProcess(StateContext ctx) {
		System.out.println("internal process for psedu");
	}

}
