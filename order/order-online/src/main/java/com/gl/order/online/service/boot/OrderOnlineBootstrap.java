package com.gl.order.online.service.boot;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderOnlineBootstrap {
    private static final Logger log = LoggerFactory.getLogger(OrderOnlineBootstrap.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/order-online-application-context.xml");

        try {
            System.in.read();
        } catch (IOException e) {
            log.error("", e);
        }
    }

}
