package com.gl.monitor.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.gl.monitor.receiver" })
public class App {
	/**
	 * mvn spring-boot:run -Dserver.port=10082 -DsysId=s9001
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
