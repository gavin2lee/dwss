package com.gl.fsm.core.transition;

import java.util.ArrayList;
import java.util.List;

import com.gl.fsm.core.FiniteStateManager;
import com.gl.fsm.core.StateContext;
import com.gl.fsm.core.state.State;

public abstract class Transition {
    private String id;
    private String name;

    private State inState;
    private State outState;

    private List<TransitionVoter> transitionVoters = new ArrayList<TransitionVoter>();
    private TransitionVotePolicy transitionVotePolicy;

    public Transition(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public void fire(StateContext ctx) {
        FiniteStateManager fsm = ctx.getFsm();
        State nextState = this.getOutState();
        if (nextState != null) {
            fsm.fire(ctx, nextState);
        }
    }

    public boolean vote(StateContext ctx) {
        if (transitionVotePolicy != null) {
            return transitionVotePolicy.vote(this, ctx);
        }

        return false;
    }

    public List<TransitionVoter> getTransitionVoters() {
        return transitionVoters;
    }

    public void setTransitionVoters(List<TransitionVoter> transitionVoters) {
        if (transitionVoters != null) {
            this.transitionVoters.addAll(transitionVoters);
        }
    }

    public void addTranstionVoters(TransitionVoter... transitionVoters) {
        for (TransitionVoter voter : transitionVoters) {
            this.transitionVoters.add(voter);
        }
    }

    public TransitionVotePolicy getTransitionVotePolicy() {
        return transitionVotePolicy;
    }

    public void setTransitionVotePolicy(TransitionVotePolicy transitionVotePolicy) {
        this.transitionVotePolicy = transitionVotePolicy;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public State getInState() {
        return inState;
    }

    public void setInState(State inState) {
        this.inState = inState;
    }

    public State getOutState() {
        return outState;
    }

    public void setOutState(State outState) {
        this.outState = outState;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transition other = (Transition) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
