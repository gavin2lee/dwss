package com.gl.fsm.core.transition;

import com.gl.fsm.core.StateContext;
import com.gl.fsm.core.event.EventType;

public class EventTypeTransitionVoter extends TransitionVoter {
    private EventType expectedEventType;
    
    public EventTypeTransitionVoter(EventType expectedEventType) {
        super();
        this.expectedEventType = expectedEventType;
    }

    @Override
    protected boolean internalVote(StateContext ctx) {
        EventType actualEventType = ctx.getEvent().getEventType();
        if(expectedEventType.equals(actualEventType)){
            return true;
        }
        return false;
    }

    public EventType getExpectedEventType() {
        return expectedEventType;
    }

}
