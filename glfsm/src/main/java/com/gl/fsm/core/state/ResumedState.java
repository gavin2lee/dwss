package com.gl.fsm.core.state;

import com.gl.fsm.core.StateContext;
import static com.gl.fsm.core.state.StateDescription.*;

public class ResumedState extends State {

	public ResumedState() {
		super(RESUMED, RESUMED_NAME);
	}

	@Override
	protected void internalProcess(StateContext ctx) {
		// TODO Auto-generated method stub

	}

}
