package com.gl.order.client.batch.spring;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/quartz-application.xml" })
public class SpringQuartzTest {
    private static final Logger log = LoggerFactory.getLogger(SpringQuartzTest.class);

    @Before
    public void setUp() {

    }

    @Test
    public void test() {
        log.info("test start");

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
