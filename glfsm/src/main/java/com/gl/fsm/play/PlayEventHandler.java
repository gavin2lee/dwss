package com.gl.fsm.play;

import java.util.HashMap;
import java.util.Map;

import com.gl.fsm.core.FiniteStateManager;
import com.gl.fsm.core.StateContext;

public class PlayEventHandler {
    private Map<String,FiniteStateManager> fsms = new HashMap<String,FiniteStateManager>();
    
    public void onEvent(PlayEvent event){
        String playerName = event.getEventData().getName();
        
        FiniteStateManager fsm = fsms.get(playerName);
        if(fsm == null){
            fsm = new FiniteStateManager();
            fsms.put(playerName, fsm);
        }else{
            StateContext ctx = null;
            
        }
    }
}
