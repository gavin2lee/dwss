package com.gl.fsm.core.transition;

import com.gl.fsm.core.StateContext;

public abstract class TransitionVotePolicy {
    public boolean vote(Transition transition,StateContext ctx){
        return internalVote(transition,ctx);
    }
    
    protected abstract boolean internalVote(Transition transition,StateContext ctx);
}
