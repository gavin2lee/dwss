package com.gl.fsm.core.transition;

import com.gl.fsm.core.StateContext;

public abstract class TransitionMatcher {
	public abstract boolean match(StateContext ctx);
}
