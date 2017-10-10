package com.gl.time.client.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gl.time.client.dto.SimplePrincipal;
import com.gl.time.client.dto.SimpleTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/application-context-reference.xml"})
public class TimeServiceClientTest {
    private static final Logger LOG = LoggerFactory.getLogger(TimeServiceClientTest.class);
    @Autowired
    TimeServiceClient client;
    
    @Test
    public void testGetTime() {
        SimplePrincipal sp = new SimplePrincipal();
        sp.setUsername("test"+System.currentTimeMillis());
        sp.setPassword(""+System.currentTimeMillis());
        
        SimpleTime st = client.getTime(sp);
        
        Assert.assertNotNull(st);
        LOG.info("TEST RET:{}", st);
    }

}
