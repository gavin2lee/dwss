package com.gl.fsm.core.state;

import static com.gl.fsm.core.state.StateDescription.PRE_APPROVED;
import static com.gl.fsm.core.state.StateDescription.PRE_APPROVED_NAME;

import com.gl.fsm.core.LeaveApplication;
import com.gl.fsm.core.StateContext;
import com.gl.fsm.core.TransitionCalculator;
import com.gl.fsm.core.transition.Transition;
import com.gl.fsm.core.transition.TransitionDescription;

public class PreApprovedState extends State {

	public PreApprovedState() {
		super(PRE_APPROVED, PRE_APPROVED_NAME,true);
		this.setTransitionCalculator(new TransitionCalculator() {
			
			@Override
			public Transition calculate(StateContext ctx) {
				LeaveApplication app = (LeaveApplication)ctx.getObject();
				String username = ctx.getUsername();
				State currentState = ctx.getObject().getCurrentState();
				
				int days = app.getDaysToApply();
				if(days >= 10) {
					return this.findTransition(currentState, TransitionDescription.T12);
				}else {
					return this.findTransition(currentState, TransitionDescription.T2);
				}
			}
		});
	}

	@Override
	protected void internalProcess(StateContext ctx) {
		
	}

}
