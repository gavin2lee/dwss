package com.gl.dwss.dirsync.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.gl.dwss.dirsync.client.service.FileSynchronizer;
import com.gl.dwss.dirsync.client.service.impl.HttpFileSynchronizer;

@SpringBootApplication(scanBasePackages = { "com.gl.dirsync.client" })
@EnableConfigurationProperties({ DirSyncServerProperties.class })
public class ClientConfig {
	private static final Logger LOG = LoggerFactory.getLogger(ClientConfig.class);

	@Bean
	public RestTemplate getRestTemplate() {
		LOG.debug("getRestTemplate");
		return new RestTemplate();
	}

	@Bean
	public FileSynchronizer getFileSynchronizer() {
		LOG.debug("getFileSynchronizer");
		return new HttpFileSynchronizer();
	}
}
