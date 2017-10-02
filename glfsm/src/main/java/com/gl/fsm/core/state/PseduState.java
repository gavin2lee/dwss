package com.gl.fsm.core.state;

import com.gl.fsm.core.StateContext;

public class PseduState extends State {

	public PseduState() {
		super("S00", "PseduState");
	}

	@Override
	protected void internalProcess(StateContext ctx) {
		System.out.println("internal process for psedu");
	}

}
