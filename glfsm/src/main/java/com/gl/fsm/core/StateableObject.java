package com.gl.fsm.core;

import com.gl.fsm.core.state.State;

public class StateableObject {
	private State currentState;

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	@Override
	public String toString() {
		return "StateableObject [currentState=" + currentState + "]";
	}
	
	
}
