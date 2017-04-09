package com.gl.dwss.dirsync.client.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.gl.dwss.dirsync.client.config.DirSyncServerProperties;
import com.gl.dwss.dirsync.client.service.FileSynchronizer;
import com.gl.dwss.dirsync.objects.ClientSyncFileDescriptor;

public class HttpFileSynchronizer implements FileSynchronizer {
	private static final Logger LOG = LoggerFactory.getLogger(HttpFileSynchronizer.class);
	private String host;
	private String port;
	private String clientRootDirPath;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DirSyncServerProperties dirSyncServerProperties;

	@PostConstruct
	public void sync() throws Exception {
		LOG.debug("sync start...");
		initProperties();
		String url = String.format("http://%s:%s/dir-sync/files", host, port);
		ResponseEntity<ClientSyncFileDescriptor> resp = restTemplate.getForEntity(url, ClientSyncFileDescriptor.class);
		if (LOG.isTraceEnabled()) {
			LOG.trace(resp.getBody().toString());
		}

		ClientSyncFileDescriptor remoteRoot = resp.getBody();
		if (remoteRoot == null) {
			return;
		}
		processRoot(remoteRoot);
	}

	protected void initProperties() {
		host = dirSyncServerProperties.getHost();
		port = String.valueOf(dirSyncServerProperties.getPort());
		clientRootDirPath = dirSyncServerProperties.getClientRootDirPath();
		LOG.debug(String.format("remote server:host %s, port %s", host, port));
	}

	protected void processRoot(ClientSyncFileDescriptor root) throws ClientProtocolException, IOException {
		String partPath = root.getFullPath();
		String path = combinePath(partPath);
		File rootFile = new File(path);
		if (!rootFile.exists()) {
			rootFile.mkdir();
		}

		List<ClientSyncFileDescriptor> childFiles = root.getChildFiles();
		for (ClientSyncFileDescriptor fd : childFiles) {
			process(fd);
		}
	}

	protected void process(ClientSyncFileDescriptor fd) throws ClientProtocolException, IOException {
		File f = new File(combinePath(fd.getFullPath()));
		if (!f.exists()) {
			if (fd.isDirectory()) {
				f.mkdirs();
			} else {
				LOG.trace(String.format("%s does not exist and downloading", fd.getFullPath()));
				downloadFile(fd);
			}
		}

		if (fd.isDirectory()) {
			for (ClientSyncFileDescriptor childFds : fd.getChildFiles()) {
				process(childFds);
			}
		}
	}

	protected void downloadFile(ClientSyncFileDescriptor f) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.custom().build();
		CloseableHttpResponse resp = null;

		try {
			String url = String.format("http://%s:%s/dir-sync/files/file?full-path=%s", host, port,
					URLEncoder.encode(f.getFullPath(), "utf-8"));
			HttpGet httpGet = new HttpGet(url);
			resp = httpclient.execute(httpGet);
			HttpEntity entity = resp.getEntity();
			if (entity != null) {
				File file = new File(combinePath(f.getFullPath()));
				OutputStream out = null;
				try {
					out = new FileOutputStream(file);
					IOUtils.copy(entity.getContent(), out);
				} finally {
					if (out != null) {
						out.close();
					}
				}
			}
		} finally {
			if (resp != null) {
				resp.close();
			}
			if (httpclient != null) {
				httpclient.close();
			}
		}
	}

	protected String combinePath(String path) {
		return clientRootDirPath + File.separator + path;
	}
}
