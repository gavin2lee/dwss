package com.gl.fsm.core.transition;

import com.gl.fsm.core.Command;
import com.gl.fsm.core.CommandDescription;
import com.gl.fsm.core.StateContext;

public class ResumeTransitionMatcher extends TransitionMatcher {

	@Override
	public boolean match(StateContext ctx) {
		Command cmd = ctx.getCommand();
		if(cmd.getCommandCode().equals(CommandDescription.RESUME)) {
			return true;
		}
		return false;
	}

}
