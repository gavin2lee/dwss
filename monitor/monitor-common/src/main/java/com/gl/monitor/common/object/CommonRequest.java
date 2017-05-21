package com.gl.monitor.common.object;

public class CommonRequest extends CommonMsgBody {
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

}
