package com.gl.fsm.core.transition;

import java.util.List;

import com.gl.fsm.core.StateContext;

public class OrTransitionVotePolicy extends TransitionVotePolicy {

    @Override
    protected boolean internalVote(Transition transition, StateContext ctx) {
        List<TransitionVoter> transitionVoters = transition.getTransitionVoters();
        for(TransitionVoter voter : transitionVoters){
            if(voter.vote(ctx)){
                return true;
            }
        }
        return false;
    }

}
