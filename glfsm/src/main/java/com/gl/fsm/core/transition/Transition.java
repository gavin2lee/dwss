package com.gl.fsm.core.transition;

import com.gl.fsm.core.FiniteStateManager;
import com.gl.fsm.core.StateContext;
import com.gl.fsm.core.state.State;

public abstract class Transition {
	private String transitionCode;
	private String transitionName;
	private State firedState;

	public Transition(String transitionCode, String transitionName, State firedState) {
		super();
		this.transitionCode = transitionCode;
		this.transitionName = transitionName;
		this.firedState = firedState;
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

	public void fire(StateContext ctx,FiniteStateManager fsm) {
		System.out.println(String.format("Fire - %s", ctx));
		fsm.fire(ctx, firedState);
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
