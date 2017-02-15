package com.gl.dwss.dirsync.pojo;

import java.util.LinkedList;
import java.util.List;

public class DirSyncFileDescriptor extends SyncFileDescriptor {
	private List<SyncFileDescriptor> childFiles = new LinkedList<SyncFileDescriptor>();

	public List<SyncFileDescriptor> getChildFiles() {
		return childFiles;
	}

	public void setChildFiles(List<SyncFileDescriptor> childFiles) {
		this.childFiles = childFiles;
	}
}
