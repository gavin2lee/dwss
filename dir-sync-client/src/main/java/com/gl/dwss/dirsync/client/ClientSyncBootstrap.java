package com.gl.dwss.dirsync.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import com.gl.dwss.dirsync.client.config.ClientConfig;

public class ClientSyncBootstrap {
	private static final Logger LOG = LoggerFactory.getLogger(ClientSyncBootstrap.class);
	

	public static void main(String[] args) {
//		SpringApplication.run(ClientConfig.class, args);
		SpringApplication app = new SpringApplication(ClientConfig.class);
		app.setWebEnvironment(false);
		app.run(args);
		LOG.debug(ClientSyncBootstrap.class.getSimpleName() +  " started");
		
		
	}

}
