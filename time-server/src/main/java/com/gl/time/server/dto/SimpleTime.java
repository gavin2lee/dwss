package com.gl.time.server.dto;

import java.io.Serializable;
import java.util.List;

public class SimpleTime implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5926610240117488155L;

    private String name;
    private List<String> timeParts;// date,time
    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTimeParts() {
        return timeParts;
    }

    public void setTimeParts(List<String> timeParts) {
        this.timeParts = timeParts;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SimpleTime [name=" + name + ", timeParts=" + timeParts + ", time=" + time + "]";
    }

}
