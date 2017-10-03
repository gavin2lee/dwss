package com.gl.fsm.core;

import com.gl.fsm.core.event.Event;
import com.gl.fsm.core.state.State;

public class StateContext {
    private State state;
    private Event<?> event;
    private Object data;

    private FiniteStateManager fsm;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Event<?> getEvent() {
        return event;
    }

    public void setEvent(Event<?> event) {
        this.event = event;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public FiniteStateManager getFsm() {
        return fsm;
    }

    public void setFsm(FiniteStateManager fsm) {
        this.fsm = fsm;
    }

}
