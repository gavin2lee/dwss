package com.gl.fsm.core.transition;

import com.gl.fsm.core.state.State;

public class SimpleTransition extends Transition {

	public SimpleTransition(String transitionCode, String transitionName, State firedState) {
		super(transitionCode, transitionName, firedState);
	}

}
