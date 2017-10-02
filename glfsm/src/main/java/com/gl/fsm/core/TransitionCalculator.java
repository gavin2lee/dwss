package com.gl.fsm.core;

import com.gl.fsm.core.transition.Transition;

public abstract class TransitionCalculator {

	public TransitionCalculator() {
		super();
	}

	public abstract Transition calculate(StateContext ctx);

}
