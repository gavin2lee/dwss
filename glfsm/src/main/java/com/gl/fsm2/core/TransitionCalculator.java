package com.gl.fsm2.core;

import com.gl.fsm2.core.state.State;
import com.gl.fsm2.core.transition.Transition;

public abstract class TransitionCalculator {

	public TransitionCalculator() {
		super();
	}

	public abstract Transition calculate(StateContext ctx);
	
	public Transition findTransition(State state, String transitionCode) {
		Transition t = state.findTransition(transitionCode);
		if(t == null) {
			throw new IllegalStateException("None potential transition found for "+state + " - "+transitionCode);
		}
		
		return t;
	}

}
