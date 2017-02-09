package com.gl.dwss.cf.objects.pojo;

import java.util.Date;

public class SimpleObject {
	private Long oid;
	private String content;
	private Date createAt;
	private Date updateAt;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return "SimpleObject [oid=" + oid + ", content=" + content + ", createAt=" + createAt + ", updateAt=" + updateAt
				+ "]";
	}

}
