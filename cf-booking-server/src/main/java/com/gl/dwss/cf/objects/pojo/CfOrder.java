package com.gl.dwss.cf.objects.pojo;

import java.util.Date;

public class CfOrder {
	private Long oid;
	private String client;
	private Double amount;
	private String operation;
	private Date createAt;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "CfOrder [oid=" + oid + ", client=" + client + ", amount=" + amount + ", operation=" + operation
				+ ", createAt=" + createAt + "]";
	}
}
