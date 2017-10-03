package com.gl.fsm.core.event;

public abstract class Event<T> {

    private String id;
    private EventType eventType;
    private T eventData;

    public Event(String id, EventType eventType, T eventData) {
        super();
        this.id = id;
        this.eventType = eventType;
        this.eventData = eventData;
    }

    public String getId() {
        return id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public T getEventData() {
        return eventData;
    }
}
