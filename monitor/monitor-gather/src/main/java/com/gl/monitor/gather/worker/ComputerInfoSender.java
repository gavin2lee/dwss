package com.gl.monitor.gather.worker;

import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gl.monitor.gather.config.ObjectHolder;
import com.gl.monitor.gather.vo.ComputerInfo;

public class ComputerInfoSender {
	private static final Logger log = LoggerFactory.getLogger(ComputerInfoSender.class);
	
	@Autowired
	private ObjectHolder holder;
	private BlockingQueue<ComputerInfo> infoQueue;
	
	public ComputerInfoSender(){
	}
	
	@PostConstruct
	public void execute(){
		infoQueue = holder.getInfoQueue();
		new Thread(new ComputerInfoProcessor()).start();
	}
	
	private class ComputerInfoProcessor implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					ComputerInfo info = infoQueue.take();
					send(info);
				} catch (InterruptedException e) {
					log.error("", e);
				}
				
			}
			
		}
		
		protected void send(ComputerInfo info){
			log.info(String.format("Send:%s", info));
		}
		
	}
	
	
}
