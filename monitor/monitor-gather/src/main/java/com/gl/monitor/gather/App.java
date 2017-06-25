package com.gl.monitor.gather;

import org.springframework.boot.SpringApplication;

import com.gl.monitor.gather.config.AppConfiguration;

public class App {

	/**
	 *  mvn spring-boot:run -DremoteServerHost=192.168.0.104 -DremoteServerPort=10080 -DsysId=client-01
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AppConfiguration.class);
		app.setWebEnvironment(false);
		app.run(args);
	}

}
