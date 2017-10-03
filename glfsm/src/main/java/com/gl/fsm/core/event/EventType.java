package com.gl.fsm.core.event;

public class EventType {
    private String id;
    private String name;

    public EventType(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
