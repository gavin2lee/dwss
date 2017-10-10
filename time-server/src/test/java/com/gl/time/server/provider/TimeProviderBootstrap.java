package com.gl.time.server.provider;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TimeProviderBootstrap {

    public static void main(String... args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/application-context.xml");
        
        System.out.println("TimeProviderBootstrap started");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
