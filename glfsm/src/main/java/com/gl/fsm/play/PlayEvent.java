package com.gl.fsm.play;

import com.gl.fsm.core.event.Event;
import com.gl.fsm.core.event.EventType;

public class PlayEvent extends Event<Player> {

    public PlayEvent(String id, EventType eventType, Player eventData) {
        super(id, eventType, eventData);
    }
    
    

}
