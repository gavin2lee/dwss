package com.gl.monitor.gather.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.gl.monitor.gather.vo.ComputerInfo;
import com.gl.monitor.gather.worker.ComputerInfoGather;

public class ComputerInfoGatherJob extends QuartzJobBean {
	private static final Logger log = LoggerFactory.getLogger(ComputerInfoGatherJob.class);

	private ComputerInfoGather gather;

	private int timeout;

	public void setGather(ComputerInfoGather gather) {
		this.gather = gather;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info(String.format("Execute Job within %s", timeout));
		ComputerInfo info = gather.gather();

		log.info(String.format("Data:%s", info));
	}
}
