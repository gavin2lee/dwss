package com.gl.order.client.config;

import org.apache.commons.lang3.StringUtils;

public class HttpServerConfiguration {
	private String schema = "http";
	private String remoteHost = "localhost";
	private String remotePort = "9009";

	public HttpServerConfiguration() {
		String host = System.getProperty("remoteServerHost");
		if (StringUtils.isNotBlank(host)) {
			remoteHost = host.trim();
		}

		String port = System.getProperty("remoteServerPort");
		if (StringUtils.isNotBlank(port)) {
			remotePort = port.trim();
		}

		String httpSchema = System.getProperty("httpSchema");
		if (StringUtils.isNotBlank(httpSchema)) {
			this.schema = httpSchema.trim();
		}
	}

	public static HttpServerConfiguration getInstance() {
		return new HttpServerConfiguration();
	}

	public String buildServerUrl() {
		StringBuilder sb = new StringBuilder();
		sb.append(schema).append("://").append(remoteHost).append(":").append(remotePort);
		return sb.toString();
	}

	public HttpServerConfiguration withSchema(String schema) {
		this.schema = schema;
		return this;
	}

	public HttpServerConfiguration withRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
		return this;
	}

	public HttpServerConfiguration withRemotePort(String remotePort) {
		this.remotePort = remotePort;
		return this;
	}

	public String getSchema() {
		return schema;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public String getRemotePort() {
		return remotePort;
	}

}
