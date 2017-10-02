package com.gl.fsm.core.transition;

import com.gl.fsm.core.StateContext;

public abstract class TransitionValidator {
	public abstract boolean validate(StateContext ctx);
}
