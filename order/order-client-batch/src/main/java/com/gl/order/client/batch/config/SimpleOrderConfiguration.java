package com.gl.order.client.batch.config;

public class SimpleOrderConfiguration {
    private String systemId = "10";
    private String clientId = "C100";

    public String getSystemId() {
        return systemId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
