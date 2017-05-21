package com.gl.monitor.common.object;

public abstract class CommonMsgBody {
	private String bizSeq;// systemid(6)+date(8)+seq(10)
	private String createOn;
	private String createAt;
	private String updateOn;// YYYYMMdd
	private String updateAt;// HHmmssSSS,101000888

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

	@Override
	public String toString() {
		return "CommonMsgBody [bizSeq=" + bizSeq + ", createOn=" + createOn + ", createAt=" + createAt + ", updateOn="
				+ updateOn + ", updateAt=" + updateAt + "]";
	}

}
