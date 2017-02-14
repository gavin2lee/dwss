package com.gl.dwss.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("global.app")
public class GlobalAppProperties {
	private String appName;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
