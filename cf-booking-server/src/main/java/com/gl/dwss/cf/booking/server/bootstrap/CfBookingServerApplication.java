package com.gl.dwss.cf.booking.server.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.gl.dwss.cf.booking.server"})
public class CfBookingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfBookingServerApplication.class, args);

	}

}
