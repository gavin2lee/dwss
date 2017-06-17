package com.gl.monitor.gather.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.gl.monitor.gather.worker.ComputerInfoGather;

public class ComputerInfoGatherJobFactory {
	@Autowired
	private ComputerInfoGather gather;

	private ComputerInfoGatherJob jobDetail;

	public ComputerInfoGather getGather() {
		return gather;
	}

	public void setGather(ComputerInfoGather gather) {
		this.gather = gather;
	}

	public void setJobDetail(ComputerInfoGatherJob jobDetail) {
		this.jobDetail = jobDetail;
	}

	public ComputerInfoGatherJob getJobDetail() {
		return jobDetail;
	}
}
