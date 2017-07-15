package com.gl.order.client.batch.simpleorder;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.gl.order.client.batch.config.SimpleOrderConfiguration;
import com.gl.order.client.builder.SimpleOrderBuilder;
import com.gl.order.client.builder.SimpleOrderReqBuilder;
import com.gl.order.client.exception.ClientException;
import com.gl.order.client.msg.SimpleOrderReq;
import com.gl.order.client.pojo.SimpleOrder;
import com.gl.order.client.sender.SimpleOrderSender;
import com.gl.order.common.msg.CommonResponse;
import com.gl.order.common.util.DateTimeUtils;

public class SimpleOrderJob extends QuartzJobBean {
	private static final Logger log = LoggerFactory.getLogger(SimpleOrderJob.class);

	@Autowired
	private SimpleOrderSender simpleOrderSender;

	private SimpleOrderConfiguration simpleOrderConfiguration = new SimpleOrderConfiguration();

	protected SimpleOrderReq buildSimpleOrderReq() {
		String messageId = simpleOrderConfiguration.getSystemId() + DateTimeUtils.date2string()
				+ System.currentTimeMillis();
		SimpleOrderReqBuilder b = new SimpleOrderReqBuilder(messageId);
		b.withClientId(simpleOrderConfiguration.getClientId()).withRequestTime(DateTimeUtils.datetime2string());

		SimpleOrder order = buildSimpleOrder();
		b.withOrder(order);

		return b.build();
	}

	protected SimpleOrder buildSimpleOrder() {
		String orderId = DateTimeUtils.date2string();
		String bizId = simpleOrderConfiguration.getSystemId() + DateTimeUtils.date2string()
				+ System.currentTimeMillis();
		String osName = System.getProperty("os.name") + "-" + System.getProperty("os.version");
		SimpleOrderBuilder b = new SimpleOrderBuilder(orderId);
		b.withBizId(bizId);
		b.withCreateTime(DateTimeUtils.datetime2string());
		b.withProductName(osName);

		return b.build();
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		SimpleOrderReq req = buildSimpleOrderReq();

		try {
			log.info("start");
			CommonResponse resp = simpleOrderSender.send(req);
			log.info("" + resp);
			log.info("end");

		} catch (ClientException e) {
			log.error("", e);
		}

	}

}
