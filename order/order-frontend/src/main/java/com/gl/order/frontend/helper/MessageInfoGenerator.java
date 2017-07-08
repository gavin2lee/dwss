package com.gl.order.frontend.helper;

import org.springframework.stereotype.Component;

import com.gl.order.common.util.DateTimeUtils;
import com.gl.order.frontend.config.SysConfiguration;

@Component
public class MessageInfoGenerator {
	private String systemId = SysConfiguration.SYSTEM_ID;

	public String generateMsgId() {
		StringBuilder msgId = new StringBuilder();
		msgId.append(systemId).append(DateTimeUtils.date2string()).append(System.currentTimeMillis());
		return msgId.toString();
	}

	public String generateRespTime() {
		return DateTimeUtils.datetime2string();
	}
}
