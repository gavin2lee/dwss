package com.gl.fsm2.core.state;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gl.fsm2.core.DefaultTransitionCalculator;
import com.gl.fsm2.core.FiniteStateManager;
import com.gl.fsm2.core.StateContext;
import com.gl.fsm2.core.TransitionCalculator;
import com.gl.fsm2.core.transition.Transition;

public abstract class State {
	private String stateCode;
	private String stateName;
	protected Set<Transition> potentialTransitions = new HashSet<Transition>();
	protected List<TransitionCalculator> transitionCalculators = new ArrayList<TransitionCalculator>();
	protected TransitionCalculator defaultTransitionCalculator = new DefaultTransitionCalculator();

	private boolean autoTransit = false;

	public State(String stateCode, String stateName) {
		this(stateCode, stateName, false);
	}

	public State(String stateCode, String stateName, boolean autoTransit) {
		super();
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.autoTransit = autoTransit;

		transitionCalculators.add(defaultTransitionCalculator);
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

		Transition target = null;
		for (TransitionCalculator transitionCalculator : transitionCalculators) {
			target = transitionCalculator.calculate(ctx);
			if (target != null) {
				break;
			}
		}

		if (target == null) {
			System.err.println(Transition.class.getSimpleName() + " cannot be found");
			return;
		}
		target.fire(ctx, fsm);
	}

	public boolean isAutoTransit() {
		return autoTransit;
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

	public Transition findTransition(String transitionCode) {
		Transition target = null;
		for (Transition t : this.getPotentialTransitions()) {
			if (transitionCode.equals(t.getTransitionCode())) {
				target = t;
				break;
			}
		}

		return target;
	}

	public void addPotentialTransitions(Transition... transitions) {
		for (Transition t : transitions) {
			getPotentialTransitions().add(t);
		}
	}

	public void setTransitionCalculator(TransitionCalculator transitionCalculator) {
		this.transitionCalculators.add(transitionCalculator);
	}

	@Override
	public String toString() {
		return "State [stateCode=" + stateCode + ", stateName=" + stateName + "]";
	}

	protected abstract void internalProcess(StateContext ctx);
}
