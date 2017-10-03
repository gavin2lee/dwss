package com.gl.fsm.core.state;

public class SimpleNamedState extends State {

    public SimpleNamedState(String id, String name) {
        super(id, name);
    }

    public SimpleNamedState(String id, String name, boolean autoTransit) {
        super(id, name, autoTransit);
    }

}
