package com.gl.dwss.dirsync.server.service;

import com.gl.dwss.dirsync.pojo.SyncFileDescriptor;

public interface DirSyncService {
	SyncFileDescriptor scanRootDir();
}
