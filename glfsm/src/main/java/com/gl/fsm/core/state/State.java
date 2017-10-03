package com.gl.fsm.core.state;

import java.util.HashSet;
import java.util.Set;

import com.gl.fsm.core.StateContext;
import com.gl.fsm.core.transition.Transition;

public abstract class State {
    private String id;
    private String name;
    private boolean autoTransit;

    private Set<Transition> inTransitions = new HashSet<Transition>();
    private Set<Transition> outTransitions = new HashSet<Transition>();

    private StateTransitor stateTransitor;

    private StateProcessor stateProcessor;

    public State(String id, String name) {
        this(id, name, false);
    }

    public State(String id, String name, boolean autoTransit) {
        this.id = id;
        this.name = name;
        this.autoTransit = autoTransit;

        init();
    }

    protected void init() {
        if (stateTransitor == null) {
            stateTransitor = new DefaultStateTransitor();
        }
    }

    public void transit(StateContext ctx) {
        stateTransitor.transit(this, ctx);
    }

    public void process(StateContext ctx) {
        if (stateProcessor != null) {
            stateProcessor.process(ctx);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAutoTransit() {
        return autoTransit;
    }

    public Set<Transition> getInTransitions() {
        return inTransitions;
    }

    public void setInTransitions(Set<Transition> inTransitions) {
        if (inTransitions != null) {

            this.inTransitions.addAll(inTransitions);
        }
    }

    public void addInTransitions(Transition... transitions) {
        for (Transition t : transitions) {
            this.inTransitions.add(t);
        }
    }

    public Set<Transition> getOutTransitions() {
        return outTransitions;
    }

    public void setOutTransitions(Set<Transition> outTransitions) {
        if (outTransitions != null) {

            this.outTransitions.addAll(outTransitions);
        }
    }

    public void addOutTransitions(Transition... transitions) {
        for (Transition t : transitions) {
            this.outTransitions.add(t);
        }
    }

    public StateTransitor getStateTransitor() {
        return stateTransitor;
    }

    public void setStateTransitor(StateTransitor stateTransitor) {
        this.stateTransitor = stateTransitor;
    }

    public StateProcessor getStateProcessor() {
        return stateProcessor;
    }

    public void setStateProcessor(StateProcessor stateProcessor) {
        this.stateProcessor = stateProcessor;
    }

    @Override
    public String toString() {
        return "State [id=" + id + ", name=" + name + "]";
    }
    

}
