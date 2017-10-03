package com.gl.fsm.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gl.fsm.core.state.State;
import com.gl.fsm.core.state.StateListener;

public class FiniteStateManager {
    private State currentState;
    private State previousState;
    private List<State> firedStates = new ArrayList<State>();
    private Set<StateListener> stateListeners = new HashSet<StateListener>();
    
    
    public FiniteStateManager(){
        init();
    }
    
    private void init(){
        
    }

    public void fire(StateContext ctx, State nextState) {
        if(ctx.getFsm() == null){
            ctx.setFsm(this);
        }
        this.setPreviousState(currentState);
        nextState.process(ctx);
        this.setCurrentState(nextState);

        ctx.setState(getCurrentState());
        
        addFiredStates(this.getCurrentState());
        
        notify(ctx);
        
        if(getCurrentState().isAutoTransit()){
            getCurrentState().transit(ctx);
        }
    }

    private void notify(StateContext ctx) {
        for(StateListener listener : stateListeners){
            listener.onStateChange(ctx.getState());
        }
    }

    public State getCurrentState() {
        return currentState;
    }

    private void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getPreviousState() {
        return previousState;
    }

    private void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    private void addFiredStates(State... states) {
        for (State s : states) {
            firedStates.add(s);
        }
    }

    public List<State> getFiredStates() {
        return Collections.unmodifiableList(this.firedStates);
    }

    public Set<StateListener> getStateListeners() {
        return stateListeners;
    }

    public void setStateListeners(Set<StateListener> stateListeners) {
        if (stateListeners != null) {
            this.stateListeners.addAll(stateListeners);
        }
    }

    public void addStateListeners(StateListener... listeners) {
        for (StateListener listener : listeners) {
            this.stateListeners.add(listener);
        }
    }

}
