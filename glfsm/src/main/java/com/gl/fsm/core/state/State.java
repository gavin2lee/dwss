package com.gl.fsm.core.state;

import java.util.HashSet;
import java.util.Set;

import com.gl.fsm.core.DefaultTransitionCalculator;
import com.gl.fsm.core.FiniteStateManager;
import com.gl.fsm.core.StateContext;
import com.gl.fsm.core.TransitionCalculator;
import com.gl.fsm.core.transition.Transition;

public abstract class State {
	private String stateCode;
	private String stateName;
	protected Set<Transition> potentialTransitions = new HashSet<Transition>();
	protected TransitionCalculator transitionCalculator = new DefaultTransitionCalculator();
	
	

	public State(String stateCode, String stateName) {
		super();
		this.stateCode = stateCode;
		this.stateName = stateName;
	}

	public void process(StateContext ctx) {
		System.out.println(String.format("Process - %s", ctx));
		System.out.println(this.getClass().getSimpleName());
		internalProcess(ctx);
		System.out.println("== Processed ==");
	}

	public void transit(StateContext ctx, FiniteStateManager fsm) {
		System.out.println(String.format("Transit - %s", ctx));

		if (ctx.getObject().getCurrentState() == null) {
			ctx.getObject().setCurrentState(this);
		}

		Transition target = transitionCalculator.calculate(ctx);
		target.fire(ctx, fsm);
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Set<Transition> getPotentialTransitions() {
		return potentialTransitions;
	}

	public void setPotentialTransitions(Set<Transition> potentialTransitions) {
		this.potentialTransitions = potentialTransitions;
	}
	
	public void addPotentialTransitions(Transition...transitions) {
		for(Transition t : transitions) {
			getPotentialTransitions().add(t);
		}
	}

	@Override
	public String toString() {
		return "State [stateCode=" + stateCode + ", stateName=" + stateName + "]";
	}

	protected abstract void internalProcess(StateContext ctx);
}
