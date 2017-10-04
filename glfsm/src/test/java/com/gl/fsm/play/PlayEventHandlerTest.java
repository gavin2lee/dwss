package com.gl.fsm.play;

import java.io.IOException;

import org.junit.Test;

public class PlayEventHandlerTest {

    @Test
    public void test() {
        PlayEventHandler handler = new PlayEventHandler();

        Player player = new Player("ABC");

        PlayerEvent initEvent = new PlayerEvent(String.valueOf(System.currentTimeMillis()), handler.findEventType("e0"),
                player);
        
        handler.onEvent(initEvent);
        
        PlayerEvent playEvent = new PlayerEvent(String.valueOf(System.currentTimeMillis()), handler.findEventType("e2"),
                player);
        
        handler.onEvent(playEvent);
        
        PlayerEvent stopEvent = new PlayerEvent(String.valueOf(System.currentTimeMillis()), handler.findEventType("e1"),
                player);
        
        
        
        handler.onEvent(stopEvent);
        
        playEvent = new PlayerEvent(String.valueOf(System.currentTimeMillis()), handler.findEventType("e2"),
                player);

        handler.onEvent(playEvent);
        handler.onEvent(playEvent);
        
        
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
