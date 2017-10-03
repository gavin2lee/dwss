package com.gl.fsm.core.state;

import java.util.Set;

import com.gl.fsm.core.StateContext;
import com.gl.fsm.core.transition.Transition;

public abstract class StateTransitor {

    public void transit(State state, StateContext ctx) {
        Transition selectedTransition = voteTransition(state, ctx);
        if (selectedTransition == null) {
            return;
        }

        selectedTransition.fire(ctx);
    }

    protected Transition voteTransition(State state, StateContext ctx) {
        Set<Transition> outTransitions = state.getOutTransitions();
        Transition selectedTransition = null;

        int count = 0;

        for (Transition t : outTransitions) {
            if (t.vote(ctx)) {
                count++;
                selectedTransition = t;
            }
        }

        if (count > 1) {
            throw new RuntimeException("More than one transitions found!");
        }

        return selectedTransition;
    }
}
