package com.gl.monitor.gather;

import org.springframework.boot.SpringApplication;

import com.gl.monitor.gather.config.AppConfiguration;

public class App {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AppConfiguration.class);
		app.setWebEnvironment(false);
		app.run(args);
	}

}
