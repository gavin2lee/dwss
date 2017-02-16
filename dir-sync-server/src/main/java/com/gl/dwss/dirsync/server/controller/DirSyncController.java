package com.gl.dwss.dirsync.server.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.dwss.dirsync.pojo.SyncFileDescriptor;
import com.gl.dwss.dirsync.server.service.DirSyncService;

@RestController
public class DirSyncController {
	private static final Logger LOG = LoggerFactory.getLogger(DirSyncController.class);

	@Autowired
	private DirSyncService dirSyncService;

	@GetMapping(path = "/")
	public SyncFileDescriptor listRootDir() {
		if (LOG.isTraceEnabled()) {
			LOG.trace(String.format("listRootDir at %s", new Date().toString()));
		}

		return dirSyncService.scanRootDir();
	}

	@GetMapping(path = "/files/file")
	public void downloadFile(@RequestParam("full-path") String fullPath, HttpServletResponse resp) {
		if (LOG.isTraceEnabled()) {
			LOG.trace(String.format("download file:%s", fullPath));
		}
		File f = new File(combinePath(fullPath));
		if (!f.exists()) {
			throw new RuntimeException(String.format("%s does not exist", fullPath));
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(f);
			out = resp.getOutputStream();
			IOUtils.copy(in, out);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOG.warn("", e);
				}
			}
		}
	}

	private String combinePath(String filePath) {
		String rootPath = new File(dirSyncService.getRootDirPath()).getParent();
		return rootPath + File.separator + filePath;
	}
}
