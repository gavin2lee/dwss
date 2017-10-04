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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        if (eventType == null) {
            if (other.eventType != null)
                return false;
        } else if (!eventType.equals(other.eventType))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", eventType=" + eventType + "]";
    }
    
    
}
