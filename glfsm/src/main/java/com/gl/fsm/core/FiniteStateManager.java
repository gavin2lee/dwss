package com.gl.fsm.core;

import java.util.HashMap;
import java.util.Map;

import com.gl.fsm.core.state.ApprovedState;
import com.gl.fsm.core.state.CancelledState;
import com.gl.fsm.core.state.ClosedState;
import com.gl.fsm.core.state.PseduState;
import com.gl.fsm.core.state.RejectedState;
import com.gl.fsm.core.state.ResumedState;
import com.gl.fsm.core.state.State;
import com.gl.fsm.core.state.ToAdvancedApproveState;
import com.gl.fsm.core.state.ToApproveState;
import com.gl.fsm.core.transition.SimpleTransition;
import com.gl.fsm.core.transition.Transition;

public class FiniteStateManager {
	private State state;

	private Map<String, State> predefinedStates = new HashMap<String, State>();

	private void init() {
		State psedu = new PseduState();
		State toApprove = new ToApproveState();
		State toAdvancedApprove = new ToAdvancedApproveState();
		State approved = new ApprovedState();
		State resumed = new ResumedState();
		State closed = new ClosedState();
		State rejected = new RejectedState();
		State cancelled = new CancelledState();

		Transition t00 = new SimpleTransition("T00", "提交", toApprove);
		Transition t0 = new SimpleTransition("T0", "撤销", cancelled);
		Transition t1 = new SimpleTransition("T1", "批准1", approved);
		Transition t2 = new SimpleTransition("T2", "批准1", toAdvancedApprove);
		Transition t3 = new SimpleTransition("T3", "拒绝1", rejected);
		Transition t4 = new SimpleTransition("T4", "撤销", cancelled);
		Transition t5 = new SimpleTransition("T5", "销假", resumed);
		Transition t6 = new SimpleTransition("T6", "批准2", approved);
		Transition t7 = new SimpleTransition("T7", "拒绝2", rejected);
		Transition t8 = new SimpleTransition("T8", "撤销", cancelled);
		Transition t9 = new SimpleTransition("T9", "关闭", closed);
		Transition t10 = new SimpleTransition("T10", "关闭", closed);
		Transition t11 = new SimpleTransition("T11", "关闭", closed);

		psedu.addPotentialTransitions(t00);
		toApprove.addPotentialTransitions(t0, t1, t2, t3);
		toAdvancedApprove.addPotentialTransitions(t6, t7, t8);
		approved.addPotentialTransitions(t4, t5);
		resumed.addPotentialTransitions(t9);

		cancelled.addPotentialTransitions(t10);
		rejected.addPotentialTransitions(t11);
	}

	public void handle(String username, Command cmd) {
		System.out.println(String.format("Handle - %s %s", username, cmd));
		System.out.println(String.format("State before: %s", state));

		StateContext ctx = new StateContext();
		ctx.setUsername(username);

		// TODO

		System.out.println(String.format("State after: %s", state));
	}

	public void fire(StateContext ctx, State state) {
		System.out.println(String.format("Fire - %s %s", state, ctx));
		state.process(ctx);
		setState(state);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
