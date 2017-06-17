package com.gl.monitor.gather.vo;

public class ComputerInfo {
	private String bizSeq;// systemid(6)+date(8)+seq(10)
	private String createOn;
	private String createAt;
	private String updateOn;// YYYYMMdd
	private String updateAt;// HHmmssSSS,101000888
	private String deviceId;
	private String deviceName;
	private String deviceType;
	private String ipAddr;
	private String hostName;
	private String oprDate;
	private String oprTime;

	private long totalMemory;// k
	private long usedMemory;// k

	private int cpuCores;
	private double usedCpuPercent;

	private long totalDisk;// M
	private long usedDisk;// M

	public String getBizSeq() {
		return bizSeq;
	}

	public void setBizSeq(String bizSeq) {
		this.bizSeq = bizSeq;
	}

	public String getCreateOn() {
		return createOn;
	}

	public void setCreateOn(String createOn) {
		this.createOn = createOn;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(String updateOn) {
		this.updateOn = updateOn;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getOprDate() {
		return oprDate;
	}

	public void setOprDate(String oprDate) {
		this.oprDate = oprDate;
	}

	public String getOprTime() {
		return oprTime;
	}

	public void setOprTime(String oprTime) {
		this.oprTime = oprTime;
	}

	public long getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public long getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(long usedMemory) {
		this.usedMemory = usedMemory;
	}

	public int getCpuCores() {
		return cpuCores;
	}

	public void setCpuCores(int cpuCores) {
		this.cpuCores = cpuCores;
	}

	public double getUsedCpuPercent() {
		return usedCpuPercent;
	}

	public void setUsedCpuPercent(double usedCpuPercent) {
		this.usedCpuPercent = usedCpuPercent;
	}

	public long getTotalDisk() {
		return totalDisk;
	}

	public void setTotalDisk(long totalDisk) {
		this.totalDisk = totalDisk;
	}

	public long getUsedDisk() {
		return usedDisk;
	}

	public void setUsedDisk(long usedDisk) {
		this.usedDisk = usedDisk;
	}

	@Override
	public String toString() {
		return "ComputerInfo [bizSeq=" + bizSeq + ", createOn=" + createOn + ", createAt=" + createAt + ", updateOn="
				+ updateOn + ", updateAt=" + updateAt + ", deviceId=" + deviceId + ", deviceName=" + deviceName
				+ ", deviceType=" + deviceType + ", ipAddr=" + ipAddr + ", hostName=" + hostName + ", oprDate="
				+ oprDate + ", oprTime=" + oprTime + ", totalMemory=" + totalMemory + ", usedMemory=" + usedMemory
				+ ", cpuCores=" + cpuCores + ", usedCpuPercent=" + usedCpuPercent + ", totalDisk=" + totalDisk
				+ ", usedDisk=" + usedDisk + "]";
	}

	
}
