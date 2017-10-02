package com.gl.fsm.core;

import static com.gl.fsm.core.state.StateDescription.CANCELLED;
import static com.gl.fsm.core.state.StateDescription.REJECTED;
import static com.gl.fsm.core.state.StateDescription.RESUMED;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gl.fsm.core.state.ApprovedState;
import com.gl.fsm.core.state.CancelledState;
import com.gl.fsm.core.state.ClosedState;
import com.gl.fsm.core.state.PreApprovedState;
import com.gl.fsm.core.state.PseduState;
import com.gl.fsm.core.state.RejectedState;
import com.gl.fsm.core.state.ResumedState;
import com.gl.fsm.core.state.State;
import com.gl.fsm.core.state.StateDescription;
import com.gl.fsm.core.state.ToApproveState;
import com.gl.fsm.core.state.ToL2ApproveState;
import com.gl.fsm.core.transition.ApproveTransitionMatcher;
import com.gl.fsm.core.transition.CancelTransitionMatcher;
import com.gl.fsm.core.transition.RejectTransitionMatcher;
import com.gl.fsm.core.transition.ResumeTransitionMatcher;
import com.gl.fsm.core.transition.SimpleTransition;
import com.gl.fsm.core.transition.Transition;
import com.gl.fsm.core.transition.TransitionValidator;

public class FiniteStateManager {

	private State state;
	private State previousState;

	private StateContext stateContext;

	private Map<String, State> predefinedStates = new HashMap<String, State>();

	public FiniteStateManager() {
		init();
	}

	private void init() {
		State psedu = new PseduState();
		State toApprove = new ToApproveState();
		State toAdvancedApprove = new ToL2ApproveState();
		State approved = new ApprovedState();
		State resumed = new ResumedState();
		State closed = new ClosedState();
		State rejected = new RejectedState();
		State cancelled = new CancelledState();
		State preApproved = new PreApprovedState();

		RejectTransitionMatcher rejectTransitionMatcher = new RejectTransitionMatcher();
		CancelTransitionMatcher cancelTransitionMatcher = new CancelTransitionMatcher();
		ApproveTransitionMatcher approveTransitionMatcher = new ApproveTransitionMatcher();
		
		
		TransitionValidator selfTransitionValidator = new TransitionValidator() {

			@Override
			public boolean validate(StateContext ctx) {
				LeaveApplication app = (LeaveApplication) ctx.getObject();
				String username = ctx.getUsername();
				if (!username.equals(app.getUsername())) {
					return false;
				}
				
				return true;
			}
			
		};

		Transition t00 = new SimpleTransition("T00", "提交", toApprove);
		Transition t0 = new SimpleTransition("T0", "撤销", cancelled);
		t0.setTransitionMatcher(cancelTransitionMatcher);
		t0.setPreTransitionValidator(selfTransitionValidator);

		Transition t1 = new SimpleTransition("T1", "批准1", preApproved);
		t1.setTransitionMatcher(approveTransitionMatcher);
		t1.setPreTransitionValidator(new TransitionValidator() {

			@Override
			public boolean validate(StateContext ctx) {
				LeaveApplication app = (LeaveApplication) ctx.getObject();
				String username = ctx.getUsername();
				if (username.equals(app.getUsername())) {
					return false;
				}

				if (!username.equals("M1")) {
					return false;
				}
				return true;
			}
		});

		Transition t2 = new SimpleTransition("T2", "自动跳转", approved);
		Transition t3 = new SimpleTransition("T3", "拒绝1", rejected);
		t3.setTransitionMatcher(rejectTransitionMatcher);

		Transition t4 = new SimpleTransition("T4", "撤销", cancelled);
		t4.setTransitionMatcher(cancelTransitionMatcher);
		t4.setPreTransitionValidator(selfTransitionValidator);

		Transition t5 = new SimpleTransition("T5", "销假", resumed);
		t5.setTransitionMatcher(new ResumeTransitionMatcher());
		t5.setPreTransitionValidator(selfTransitionValidator);

		Transition t6 = new SimpleTransition("T6", "批准2", approved);
		t6.setTransitionMatcher(approveTransitionMatcher);
		t6.setPreTransitionValidator(new TransitionValidator() {

			@Override
			public boolean validate(StateContext ctx) {
				LeaveApplication app = (LeaveApplication) ctx.getObject();
				String username = ctx.getUsername();
				if (username.equals(app.getUsername())) {
					return false;
				}

				if (!username.equals("M2")) {
					return false;
				}
				return true;
			}
		});


		Transition t7 = new SimpleTransition("T7", "拒绝2", rejected);
		t7.setTransitionMatcher(rejectTransitionMatcher);

		Transition t8 = new SimpleTransition("T8", "撤销", cancelled);
		t8.setTransitionMatcher(cancelTransitionMatcher);
		t8.setPreTransitionValidator(selfTransitionValidator);

		Transition t9 = new SimpleTransition("T9", "关闭", closed);
		Transition t10 = new SimpleTransition("T10", "关闭", closed);
		Transition t11 = new SimpleTransition("T11", "关闭", closed);
		Transition t12 = new SimpleTransition("T12", "自动跳转", toAdvancedApprove);

		psedu.addPotentialTransitions(t00);
		toApprove.addPotentialTransitions(t0, t1, t3);
		toAdvancedApprove.addPotentialTransitions(t6, t7, t8);
		approved.addPotentialTransitions(t4, t5);
		resumed.addPotentialTransitions(t9);
		preApproved.addPotentialTransitions(t2, t12);

		cancelled.addPotentialTransitions(t10);
		rejected.addPotentialTransitions(t11);

		addState(//
				psedu, //
				toApprove, //
				toAdvancedApprove, //
				approved, //
				resumed, //
				cancelled, //
				rejected, //
				closed, //
				preApproved//
		);

	}

	private void addState(State... states) {
		for (State s : states) {
			predefinedStates.put(s.getStateCode(), s);
		}
	}

	public void clear() {
		System.err.println("\n== Clear ==\n");
		this.setPreviousState(null);
		this.setCurrentState(null);
		this.setStateContext(null);
	}

	public void start(String username, StateableObject object) {
		System.out.println(String.format("Start - %s %s", username, object));
		StateContext ctx = new StateContext();
		ctx.setUsername(username);
		State pseduState = findState(StateDescription.PSEDU);
		object.setCurrentState(pseduState);
		ctx.setObject(object);
		ctx.setCommand(new Command(CommandDescription.SUBMIT));

		setCurrentState(pseduState);
		setStateContext(ctx);

		pseduState.process(ctx);
		pseduState.transit(ctx, this);
	}

	public void handle(String username, Command cmd) {
		System.out.println(String.format("Handle - %s %s", username, cmd));
		System.out.println(String.format("State before: %s", getCurrentState()));

		StateContext ctx = getStateContext();
		ctx.setUsername(username);

		ctx.setCommand(cmd);
		getCurrentState().transit(ctx, this);

		System.out.println(String.format("State after: %s", getCurrentState()));
		
		check(this);
	}
	
	public  void check(FiniteStateManager fsm) {
		List<String> toCloseStateCodes = Arrays.asList(CANCELLED,REJECTED,RESUMED);
		State currentState = fsm.getCurrentState();
		if(toCloseStateCodes.contains(currentState.getStateCode())) {
			Command closeCmd = new Command(CommandDescription.CLOSE, CommandDescription.CLOSE_NAME);
			fsm.handle("SYS", closeCmd);
		}
	}

	public void fire(StateContext ctx, State firedState) {
		setPreviousState(getCurrentState());
		System.err.println(String.format("FSM Fire - %s %s", firedState, ctx));
		firedState.process(ctx);
		//
		setCurrentState(firedState);
		
		setStateContext(ctx.setState(getCurrentState()));

		if (getCurrentState().isAutoTransit()) {
			getCurrentState().transit(ctx, this);
		}
	}

	public State getCurrentState() {
		return state;
	}

	public State findState(String stateCode) {
		return predefinedStates.get(stateCode);
	}

	public void setCurrentState(State state) {
		this.state = state;
	}

	public StateContext getStateContext() {
		return stateContext;
	}

	private void setStateContext(StateContext stateContext) {
		this.stateContext = stateContext;
	}

	public State getPreviousState() {
		return previousState;
	}

	public void setPreviousState(State previousState) {
		this.previousState = previousState;
	}

}
