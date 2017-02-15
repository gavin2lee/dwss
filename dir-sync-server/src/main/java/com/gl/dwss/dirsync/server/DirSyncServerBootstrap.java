package com.gl.dwss.dirsync.server;

import org.springframework.boot.SpringApplication;

import com.gl.dwss.dirsync.server.config.DirSyncServerConfiguration;

public class DirSyncServerBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(DirSyncServerConfiguration.class, args);
	}

}
