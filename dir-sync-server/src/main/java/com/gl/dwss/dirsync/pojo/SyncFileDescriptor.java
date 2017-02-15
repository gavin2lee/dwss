package com.gl.dwss.dirsync.pojo;

import java.util.LinkedList;
import java.util.List;

public class SyncFileDescriptor {
	private Long oid;
	private String fileName;
	private String fullPath;
	private boolean isDirectory;
	private List<String> fileAttrs = new LinkedList<String>();

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public List<String> getFileAttrs() {
		return fileAttrs;
	}

	public void setFileAttrs(List<String> fileAttrs) {
		this.fileAttrs = fileAttrs;
	}

	@Override
	public String toString() {
		return "SyncFileDescriptor [oid=" + oid + ", fileName=" + fileName + ", fullPath=" + fullPath + ", isDirectory="
				+ isDirectory + ", fileAttrs=" + fileAttrs + "]";
	}

}
