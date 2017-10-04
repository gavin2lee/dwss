package com.gl.fsm.play;

import java.util.HashMap;
import java.util.Map;

import com.gl.fsm.core.FiniteStateManager;
import com.gl.fsm.core.event.EventType;
import com.gl.fsm.core.state.DefaultStateListener;
import com.gl.fsm.core.state.SimpleNamedState;
import com.gl.fsm.core.state.State;
import com.gl.fsm.core.transition.AndTransitionVotePolicy;
import com.gl.fsm.core.transition.EventTypeTransitionVoter;
import com.gl.fsm.core.transition.SimpleNamedTransition;
import com.gl.fsm.core.transition.Transition;
import com.gl.fsm.core.transition.TransitionVotePolicy;
import com.gl.fsm.core.transition.TransitionVoter;

public class PlayEventHandler {
    private Map<String,FiniteStateManager> fsms = new HashMap<String,FiniteStateManager>();
    
    private Map<String,State> predefinedStates = new HashMap<String,State>();
    private Map<String,EventType> predefinedEventTypes = new HashMap<String,EventType>();
    
    
    
    public PlayEventHandler(){
        State stopped = new SimpleNamedState("s1", "StoppedState");
        State playing = new SimpleNamedState("s2", "PlayingState");
        State paused = new SimpleNamedState("s3","PausedState");
        State psedu = new SimpleNamedState("s0","InitState");
        
        EventType play = new EventType("e2","PlayEvent");
        EventType stop = new EventType("e1","StopEvent");
        EventType pause = new EventType("e3","PauseEvent");
        EventType init = new EventType("e0","InitEvent");
        
        TransitionVoter pauseEventVoter = new EventTypeTransitionVoter(pause);
        TransitionVoter playEventVoter = new EventTypeTransitionVoter(play);
        TransitionVoter stopEventVoter = new EventTypeTransitionVoter(stop);
        
        
        TransitionVotePolicy andPolicy = new AndTransitionVotePolicy();
        
        
        
        Transition t0 = new SimpleNamedTransition("t0","init");
        t0.setInState(psedu);
        t0.setOutState(stopped);
        
        Transition t1 = new SimpleNamedTransition("t1","play");
        t1.setInState(stopped);
        t1.setOutState(playing);
        
        Transition t2 = new SimpleNamedTransition("t2","stop");
        t2.setTransitionVotePolicy(andPolicy);
        t2.setInState(playing);
        t2.setOutState(stopped);
        t2.addTranstionVoters(stopEventVoter);
        
        
        Transition t3 = new SimpleNamedTransition("t3","pause");
        t3.setTransitionVotePolicy(andPolicy);
        t3.setInState(playing);
        t3.setOutState(paused);
        t3.addTranstionVoters(pauseEventVoter);
        
        Transition t4 = new SimpleNamedTransition("t4","play");
        t4.setTransitionVotePolicy(andPolicy);
        t4.setInState(paused);
        t4.setOutState(playing);
        t4.addTranstionVoters(playEventVoter);
        
        Transition t5 = new SimpleNamedTransition("t5","stop");
        t5.setTransitionVotePolicy(andPolicy);
        t5.setInState(paused);
        t5.setOutState(stopped);
        t5.addTranstionVoters(stopEventVoter);
        
        addStates(psedu,paused,playing,stopped);
        addEventTypes(play,stop,pause,init);
    }
    
    private void addEventTypes(EventType...types){
        for(EventType t : types){
            this.predefinedEventTypes.put(t.getId(), t);
        }
    }
    
    private void addStates(State...states){
        for(State s : states){
            this.predefinedStates.put(s.getId(), s);
        }
    }
    
    private State findState(String id){
        return this.predefinedStates.get(id);
    }
    
    public EventType findEventType(String id){
        return predefinedEventTypes.get(id);
    }
    
    public void onEvent(PlayerEvent event){
        System.out.println(this.getClass().getSimpleName() + " onEvent : "+event);
        String playerName = event.getEventData().getName();
        
        FiniteStateManager fsm = fsms.get(playerName);
        if(fsm == null){
            fsm = new FiniteStateManager();
            fsm.setPseduState(findState("s0"));
            fsm.addStateListeners(new DefaultStateListener());
            fsms.put(playerName, fsm);
            
        }
        
        fsm.handleEvent(event);
        
        System.out.println(fsm.getCurrentState());
    }
}
