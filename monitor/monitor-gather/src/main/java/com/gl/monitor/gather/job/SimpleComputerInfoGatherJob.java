package com.gl.monitor.gather.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gl.monitor.gather.vo.ComputerInfo;
import com.gl.monitor.gather.worker.ComputerInfoGather;

public class SimpleComputerInfoGatherJob {
	private static final Logger log = LoggerFactory.getLogger(SimpleComputerInfoGatherJob.class);
	@Autowired
	private ComputerInfoGather gather;

	private int timeout;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void execute() {
		log.info(String.format("Execute Job within %s", timeout));
		ComputerInfo info = gather.gather();

		log.info(String.format("Data:%s", info));
	}
}
