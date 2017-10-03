package com.gl.fsm2.core.transition;

import java.util.ArrayList;
import java.util.List;

import com.gl.fsm2.core.FiniteStateManager;
import com.gl.fsm2.core.StateContext;
import com.gl.fsm2.core.state.State;

public abstract class Transition {
	private String transitionCode;
	private String transitionName;
	private State firedState;

	private TransitionMatcher transitionMatcher;

	private List<TransitionValidator> preTransitionValidators = new ArrayList<TransitionValidator>();

	public Transition(String transitionCode, String transitionName, State firedState) {
		super();
		this.transitionCode = transitionCode;
		this.transitionName = transitionName;
		this.firedState = firedState;
	}

	public boolean tryMatch(StateContext ctx) {
		if (transitionMatcher != null) {
			return transitionMatcher.match(ctx);
		}

		return false;
	}

	public String getTransitionCode() {
		return transitionCode;
	}

	public String getTransitionName() {
		return transitionName;
	}

	public State getFiredState() {
		return firedState;
	}

	public boolean preFire(StateContext ctx, FiniteStateManager fsm) {
		if (preTransitionValidators == null || preTransitionValidators.isEmpty()) {
			return true;
		}

		for (TransitionValidator v : preTransitionValidators) {
			if (v.validate(ctx) == false) {
				return false;
			}
		}

		return true;
	}

	public void fire(StateContext ctx, FiniteStateManager fsm) {
		if (!preFire(ctx, fsm)) {
			System.err.println("Cannot proceed and validation failed");
			return;
		}

		System.out.println(String.format("Fire - %s", ctx));
		fsm.fire(ctx, firedState);
	}

	public TransitionMatcher getTransitionMatcher() {
		return transitionMatcher;
	}

	public void setTransitionMatcher(TransitionMatcher transitionMatcher) {
		this.transitionMatcher = transitionMatcher;
	}

	public List<TransitionValidator> getPreTransitionValidators() {
		return preTransitionValidators;
	}

	public void setPreTransitionValidators(List<TransitionValidator> preTransitionValidators) {
		this.preTransitionValidators = preTransitionValidators;
	}

	public void setPreTransitionValidator(TransitionValidator validator) {
		if (preTransitionValidators != null) {
			preTransitionValidators.add(validator);
		}
	}

	@Override
	public String toString() {
		String state = (getFiredState() == null ? "null" : getFiredState().getStateName());
		return "Transition [transitionCode=" + transitionCode + ", transitionName=" + transitionName + ", firedState="
				+ state + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transitionCode == null) ? 0 : transitionCode.hashCode());
		result = prime * result + ((transitionName == null) ? 0 : transitionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transition other = (Transition) obj;
		if (transitionCode == null) {
			if (other.transitionCode != null)
				return false;
		} else if (!transitionCode.equals(other.transitionCode))
			return false;
		if (transitionName == null) {
			if (other.transitionName != null)
				return false;
		} else if (!transitionName.equals(other.transitionName))
			return false;
		return true;
	}

}
