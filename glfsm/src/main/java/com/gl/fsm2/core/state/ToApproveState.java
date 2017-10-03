package com.gl.fsm2.core.state;

import java.util.Date;

import com.gl.fsm2.core.Command;
import com.gl.fsm2.core.CommandDescription;
import com.gl.fsm2.core.LeaveApplication;
import com.gl.fsm2.core.StateContext;
import com.gl.fsm2.core.StateableObject;
import com.gl.fsm2.core.TransitionCalculator;
import com.gl.fsm2.core.transition.Transition;
import com.gl.fsm2.core.transition.TransitionDescription;

public class ToApproveState extends State {

	public ToApproveState() {
		this(StateDescription.TO_APPROVE, StateDescription.TO_APPROVE_NAME);
	}

	public ToApproveState(String stateCode, String stateName) {
		super(stateCode, stateName);
		this.setTransitionCalculator(new TransitionCalculator() {

			@Override
			public Transition calculate(StateContext ctx) {
				StateableObject object = ctx.getObject();
				State currentState = object.getCurrentState();
				String username = ctx.getUsername();
				
				LeaveApplication application = (LeaveApplication) object;
				int days = application.getDaysToApply();
				Command cmd = ctx.getCommand();
				if(CommandDescription.CANCEL.equals(cmd.getCommandCode())) {
					if(!username.equals(application.getUsername())) {
						throw new RuntimeException("username is illegal");
					}
					return currentState.findTransition(TransitionDescription.T0);
				}else if (CommandDescription.APPROVE.equals(cmd.getCommandCode())){
					application.setUpdatedBy(username);
					application.setUpdatedAt(new Date());
					application.setFirstApprover(username);
					return currentState.findTransition(TransitionDescription.T1);
				}else if(CommandDescription.REJECT.equals(cmd.getCommandCode())) {
					application.setUpdatedBy(username);
					application.setUpdatedAt(new Date());
					application.setFirstApprover(username);
					return currentState.findTransition(TransitionDescription.T3);
				}else {
					return null;
				}
			}
			
		});
	}

	@Override
	protected void internalProcess(StateContext ctx) {
		// TODO Auto-generated method stub

	}

}
