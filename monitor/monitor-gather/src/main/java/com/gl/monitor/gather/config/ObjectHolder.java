package com.gl.monitor.gather.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.gl.monitor.gather.vo.ComputerInfo;

public class ObjectHolder {
	private BlockingQueue<ComputerInfo> infoQueue  = new ArrayBlockingQueue<>(1000);
	
	public BlockingQueue<ComputerInfo> getInfoQueue(){
		return infoQueue;
	}
}
