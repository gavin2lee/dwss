package com.gl.dwss.dirsync.server.service.impl;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gl.dwss.dirsync.pojo.DirSyncFileDescriptor;
import com.gl.dwss.dirsync.pojo.FileSyncFileDescriptor;
import com.gl.dwss.dirsync.pojo.SyncFileDescriptor;
import com.gl.dwss.dirsync.server.service.DirSyncService;

@Service
public class DefaultDirSyncService implements DirSyncService {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultDirSyncService.class);
	private String rootDirPath = "/home/gavin/Dev/Books";

	public SyncFileDescriptor scanRootDir() {
		if(LOG.isTraceEnabled()){
			LOG.trace(String.format("scan root directory at %s", new Date().toString()));
		}
		File rootDir = new File(rootDirPath);
		SyncFileDescriptor rootDirFd = transformBasicDescription(rootDir);
		((DirSyncFileDescriptor)rootDirFd).getChildFiles().addAll(scanChild(rootDir));
		
		return rootDirFd;
	}
	
	protected List<SyncFileDescriptor> scanChild(File parent){
		if(!parent.isDirectory()){
			throw new IllegalStateException("not a directory");
		}
		
		List<SyncFileDescriptor> childFiles = new LinkedList<SyncFileDescriptor>();
		File[] files = parent.listFiles();
		
		for(File f : files ){
			SyncFileDescriptor fd = transformBasicDescription(f);
			if(fd.isDirectory()){
				((DirSyncFileDescriptor)fd).getChildFiles().addAll(scanChild(f));
			}
			childFiles.add(fd);
		}
		
		return childFiles;
	}
	
	private SyncFileDescriptor transformBasicDescription(File f){
		SyncFileDescriptor fd = null;
		if(f.isDirectory()){
			fd = new DirSyncFileDescriptor();
		}else{
			fd = new FileSyncFileDescriptor();
		}
		fd.setFileName(f.getName());
		fd.setDirectory(f.isDirectory());
		fd.setFullPath(f.getAbsolutePath());
		
		return fd;
	}

}
