package com.gl.fsm.core.state;

public class DefaultStateListener extends StateListener {

    @Override
    public void onStateChange(State state) {
        super.onStateChange(state);
        System.out.println(" =========  ");
        System.out.println("Listener - "+ state);
        System.out.println(" =========  ");
    }
    
}
