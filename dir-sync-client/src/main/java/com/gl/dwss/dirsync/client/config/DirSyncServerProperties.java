package com.gl.dwss.dirsync.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("dir.sync.server")
public class DirSyncServerProperties {
	private String protocol = "http";
	private String host = "localhost";
	private int port = 10086;

	private String clientRootDirPath = "/home/gavin/Dev/Tmp";

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getClientRootDirPath() {
		return clientRootDirPath;
	}

	public void setClientRootDirPath(String clientRootDirPath) {
		this.clientRootDirPath = clientRootDirPath;
	}

}
