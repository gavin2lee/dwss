package com.gl.order.common.pojo;

public abstract class BaseOrder implements Order {
	protected String orderId;
	protected String createTime;
	protected String updateTime;
	protected String bizId;

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	@Override
	public String getOrderId() {
		return orderId;
	}

	@Override
	public String getCreateTime() {
		return createTime;
	}

	@Override
	public String getUpdateTime() {
		return updateTime;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "BaseOrder [orderId=" + orderId + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
