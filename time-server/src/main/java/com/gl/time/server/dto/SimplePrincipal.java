package com.gl.time.server.dto;

import java.io.Serializable;

public class SimplePrincipal implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4538906118654513566L;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SimplePrincipal [username=" + username + ", password=" + password + "]";
    }

    
}
