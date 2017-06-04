package com.gl.monitor.receiver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.monitor.common.object.CommonMsg;
import com.gl.monitor.common.object.MonitorReqMsg;
import com.gl.monitor.receiver.service.MonitorMessageHandler;

@RestController
public class MonitorReceiverController {
	private static final Logger log = LoggerFactory.getLogger(MonitorReceiverController.class);
	
	@Autowired
	private MonitorMessageHandler msgHandler;
	
	@PostMapping(path = "/messages")
	public CommonMsg receive(@RequestBody MonitorReqMsg reqMsg){
		log.debug("receive:\n"+reqMsg);
		CommonMsg respMsg = msgHandler.handle(reqMsg);
		log.debug("send:\n"+respMsg);
		return respMsg;
	}
	
	@GetMapping(path="/messages")
	public List<CommonMsg> list(){
		log.debug(String.format("list", ""));
		return msgHandler.list();
	}
	
	@GetMapping(path = "/messages/template")
	public CommonMsg template(){
		CommonMsg respMsg = msgHandler.getTemplate();
		return respMsg;
	}
}
