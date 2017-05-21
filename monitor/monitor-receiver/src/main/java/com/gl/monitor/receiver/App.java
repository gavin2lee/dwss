package com.gl.monitor.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.gl.monitor.receiver" })
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
