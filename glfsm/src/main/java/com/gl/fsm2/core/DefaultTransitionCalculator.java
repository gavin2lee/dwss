package com.gl.fsm2.core;

import com.gl.fsm2.core.transition.Transition;

public class DefaultTransitionCalculator extends TransitionCalculator {
	public Transition calculate(StateContext ctx) {
		if (ctx.getObject().getCurrentState().getPotentialTransitions().isEmpty()) {
			throw new IllegalStateException("No transition found!");
		}

		Transition target = null;

		if (ctx.getObject().getCurrentState().getPotentialTransitions().size() == 1) {
			for (Transition t : ctx.getObject().getCurrentState().getPotentialTransitions()) {
				target = t;
				break;
			}
		} else {
			for (Transition t : ctx.getObject().getCurrentState().getPotentialTransitions()) {
				if(t.tryMatch(ctx)) {
					target = t;
					break;
				}
			}
		}

		return target;
	}
}
