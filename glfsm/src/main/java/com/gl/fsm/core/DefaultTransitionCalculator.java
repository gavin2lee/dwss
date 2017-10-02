package com.gl.fsm.core;

import com.gl.fsm.core.transition.Transition;

public class DefaultTransitionCalculator extends TransitionCalculator {
	public Transition calculate(StateContext ctx) {
		if (ctx.getObject().getCurrentState().getPotentialTransitions().isEmpty()) {
			throw new IllegalStateException("No transition found!");
		}

		if (ctx.getObject().getCurrentState().getPotentialTransitions().size() > 1) {
			throw new UnsupportedOperationException("Not supported");
		}

		Transition target = null;
		for (Transition t : ctx.getObject().getCurrentState().getPotentialTransitions()) {
			target = t;
			break;
		}

		return target;
	}
}
