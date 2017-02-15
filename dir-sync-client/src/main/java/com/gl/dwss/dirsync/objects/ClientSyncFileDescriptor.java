package com.gl.dwss.dirsync.objects;

import java.util.LinkedList;
import java.util.List;

public class ClientSyncFileDescriptor {
	private String fileName;
	private String fullPath;
	private boolean isDirectory;
	private String suffix;
	private List<String> fileAttrs = new LinkedList<String>();
	private List<ClientSyncFileDescriptor> childFiles = new LinkedList<ClientSyncFileDescriptor>();
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public boolean isDirectory() {
		return isDirectory;
	}
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public List<String> getFileAttrs() {
		return fileAttrs;
	}
	public void setFileAttrs(List<String> fileAttrs) {
		this.fileAttrs = fileAttrs;
	}
	public List<ClientSyncFileDescriptor> getChildFiles() {
		return childFiles;
	}
	public void setChildFiles(List<ClientSyncFileDescriptor> childFiles) {
		this.childFiles = childFiles;
	}
	
	
}
