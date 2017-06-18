package com.gl.monitor.gather.service;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl.monitor.gather.vo.ComputerInfo;

public class ComputerInfoInternalSender {
	private static final Logger log = LoggerFactory.getLogger(ComputerInfoInternalSender.class);
	private BlockingQueue<ComputerInfo> infoQueue;
	
	public ComputerInfoInternalSender(BlockingQueue<ComputerInfo> queue){
		this.infoQueue = queue;
	}

	public void send(ComputerInfo info) {
		info.setBizSeq(String.format("911%s", System.nanoTime()));

		try {
			infoQueue.put(info);
		} catch (InterruptedException e) {
			log.error("", e);
		}
	}
}
