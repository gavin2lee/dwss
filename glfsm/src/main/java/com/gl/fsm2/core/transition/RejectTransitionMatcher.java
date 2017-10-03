package com.gl.fsm2.core.transition;

import com.gl.fsm2.core.Command;
import com.gl.fsm2.core.CommandDescription;
import com.gl.fsm2.core.StateContext;

public class RejectTransitionMatcher extends TransitionMatcher {

	@Override
	public boolean match(StateContext ctx) {
		Command cmd = ctx.getCommand();
		if(cmd.getCommandCode().equals(CommandDescription.REJECT)) {
			return true;
		}
		return false;
	}

}
