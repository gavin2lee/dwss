package com.gl.fsm.core.transition;

import com.gl.fsm.core.StateContext;

public abstract class TransitionVoter {
    public boolean vote(StateContext ctx){
        return internalVote(ctx);
    }
    
    protected abstract boolean internalVote(StateContext ctx);
}
