package com.gl.fsm.core.state;

import com.gl.fsm.core.StateContext;

public abstract class StateProcessor {
    public void process(StateContext ctx){
        internalProcess(ctx);
    }
    
    protected abstract void internalProcess(StateContext ctx);
}
