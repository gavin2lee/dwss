package com.gl.order.frontend.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = { "com.gl.order.frontend" })
@ImportResource(locations={"classpath:spring/order-frontend-dubbo-consumer.xml"})
public class AppConfiguration {

}
