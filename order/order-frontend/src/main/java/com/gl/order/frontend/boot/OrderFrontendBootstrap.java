package com.gl.order.frontend.boot;

import org.springframework.boot.SpringApplication;

import com.gl.order.frontend.config.AppConfiguration;

public class OrderFrontendBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }

}
