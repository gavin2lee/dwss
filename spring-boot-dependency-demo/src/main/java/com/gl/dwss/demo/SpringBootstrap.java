package com.gl.dwss.demo;

import org.springframework.boot.SpringApplication;

import com.gl.dwss.config.SpringBootDemoConfig;

/**
 * java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n \
-jar target/myproject-0.0.1-SNAPSHOT.jar
 * @author gavin
 *
 */
public class SpringBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoConfig.class, args);
	}

}
