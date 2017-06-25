package com.gl.monitor.receiver.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gl.monitor.common.object.CommonMsg;
import com.gl.monitor.common.object.CommonMsgBuilder;
import com.gl.monitor.common.object.MonitorReqMsg;

@Service("monitorMessageHandler")
public class MonitorMessageHandler {
	private static final Logger log = LoggerFactory.getLogger(MonitorMessageHandler.class);

	public CommonMsg handle(MonitorReqMsg reqMsg) {
		log.info("MonitorReqMsg:" + reqMsg);
		if (reqMsg == null) {

			return CommonMsgBuilder.buildDefMsg();
		}
		log.debug("receive message header:" + reqMsg.getHeader());
		log.debug("receive message body:" + reqMsg.getBody());

		CommonMsg respMsg = CommonMsgBuilder.buildMsg(reqMsg);
		return respMsg;
	}

	public List<CommonMsg> list() {
		return Collections.emptyList();
	}

	public CommonMsg getTemplate() {
		CommonMsg respMsg = CommonMsgBuilder.buildReqMsgTemplate();
		return respMsg;
	}
}
