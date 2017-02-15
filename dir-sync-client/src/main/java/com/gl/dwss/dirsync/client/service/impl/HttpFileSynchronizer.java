package com.gl.dwss.dirsync.client.service.impl;

import java.io.File;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.gl.dwss.dirsync.client.service.FileSynchronizer;
import com.gl.dwss.dirsync.objects.ClientSyncFileDescriptor;

public class HttpFileSynchronizer implements FileSynchronizer {
	private static final Logger LOG = LoggerFactory.getLogger(HttpFileSynchronizer.class);
	private String host = "localhost";
	private String port = "10086";
	private String clientRootDirPath = "/home/gavin/Dev/Tmp";
	private File clientRootDir = new File(clientRootDirPath);

	@Autowired
	private RestTemplate restTemplate;

	@PostConstruct
	public void sync() throws Exception {
		LOG.debug("sync start...");
		String url = String.format("http://%s:%s/dir-sync/", host, port);
		ResponseEntity<ClientSyncFileDescriptor> resp = restTemplate.getForEntity(url, ClientSyncFileDescriptor.class);
		if(LOG.isTraceEnabled()){
			LOG.trace(resp.getBody().toString());
		}
	}

}
