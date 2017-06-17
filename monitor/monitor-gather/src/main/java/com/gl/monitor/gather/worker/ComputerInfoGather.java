package com.gl.monitor.gather.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.monitor.gather.service.ComputerInfoGatherService;
import com.gl.monitor.gather.vo.ComputerInfo;

@Component
public class ComputerInfoGather {
	private static final Logger log = LoggerFactory.getLogger(ComputerInfoGather.class);
	
	@Autowired
	private ComputerInfoGatherService gatherService;
	
	public ComputerInfo gather(){
		if(log.isDebugEnabled()){
			log.debug(String.format("gather"));
		}
		return gatherService.gather();
	}
	
}
