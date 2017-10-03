package com.gl.fsm2.core.transition;

import com.gl.fsm2.core.StateContext;

public abstract class TransitionMatcher {
	public abstract boolean match(StateContext ctx);
}
