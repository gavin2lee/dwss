package com.gl.fsm2.core.transition;

import com.gl.fsm2.core.StateContext;

public abstract class TransitionValidator {
	public abstract boolean validate(StateContext ctx);
}
