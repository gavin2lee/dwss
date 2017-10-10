package com.gl.time.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl.time.client.consumer.TimeProviderSkeleton;
import com.gl.time.client.dto.SimplePrincipal;
import com.gl.time.client.dto.SimpleTime;

public class TimeServiceClient {
    private static final Logger LOG = LoggerFactory.getLogger(TimeServiceClient.class);
    private TimeProviderSkeleton timeProvider;
    
    public SimpleTime getTime(SimplePrincipal principal){
        LOG.info("Client SEND:{}",principal);
        
        SimpleTime resp = timeProvider.getTime(principal);
        
        LOG.info("Client RECV:{}", resp);
        
        return resp;
    }

    public TimeProviderSkeleton getTimeProvider() {
        return timeProvider;
    }

    public void setTimeProvider(TimeProviderSkeleton timeProvider) {
        this.timeProvider = timeProvider;
    }
    
    
}
