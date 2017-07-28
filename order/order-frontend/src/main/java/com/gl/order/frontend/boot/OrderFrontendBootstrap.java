package com.gl.order.frontend.boot;

import org.springframework.boot.SpringApplication;

import com.gl.order.frontend.config.AppConfiguration;

public class OrderFrontendBootstrap {

    /**
     * mvn spring-boot:run -Dserver.port=10082 -DserverId=s9001
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }

}
