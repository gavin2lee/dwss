package com.gl.fsm2.core.transition;

import com.gl.fsm2.core.state.State;

public class SimpleTransition extends Transition {

	public SimpleTransition(String transitionCode, String transitionName, State firedState) {
		super(transitionCode, transitionName, firedState);
	}

}
