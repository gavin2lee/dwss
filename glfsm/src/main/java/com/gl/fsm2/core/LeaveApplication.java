package com.gl.fsm2.core;

import java.util.Date;

public class LeaveApplication extends StateableObject {
	private String username;
	private int daysToApply;
	private Date createdAt;
	private Date updatedAt;
	private String updatedBy;

	private String firstApprover;
	private String secondApprover;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDaysToApply() {
		return daysToApply;
	}

	public void setDaysToApply(int daysToApply) {
		this.daysToApply = daysToApply;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getFirstApprover() {
		return firstApprover;
	}

	public void setFirstApprover(String firstApprover) {
		this.firstApprover = firstApprover;
	}

	public String getSecondApprover() {
		return secondApprover;
	}

	public void setSecondApprover(String secondApprover) {
		this.secondApprover = secondApprover;
	}

	@Override
	public String toString() {
		String state = (getCurrentState() == null ? "null" : getCurrentState().getStateName());
		return "LeaveApplication [username=" + username + ", daysToApply=" + daysToApply + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + ",state=" + state + "]";
	}

}
