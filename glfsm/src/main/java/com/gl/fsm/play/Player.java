package com.gl.fsm.play;

public class Player {
    private String name;

    public Player(String name) {
        super();
        this.name = name;
    }

    public void play() {
        System.out.println(name + "play...");
    }

    public void pause() {
        System.out.println(name + "pause");
    }

    public void stop() {
        System.out.println(name + "stop");
    }

    public String getName() {
        return name;
    }
}
