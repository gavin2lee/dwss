package com.gl.order.client.batch.simpleorder.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.gl.order.client.batch.config.SimpleOrderConfiguration;
import com.gl.order.client.builder.SimpleOrderReqBuilder;
import com.gl.order.client.exception.ClientException;
import com.gl.order.client.msg.SimpleOrderReq;
import com.gl.order.client.sender.SimpleOrderSender;
import com.gl.order.common.dict.MessageType;
import com.gl.order.common.msg.CommonResponse;
import com.gl.order.common.util.DateTimeUtils;

public class SimpleOrderHeartBeatJob extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(SimpleOrderHeartBeatJob.class);

    @Autowired
    private SimpleOrderSender simpleOrderSender;

    private SimpleOrderConfiguration simpleOrderConfiguration = new SimpleOrderConfiguration();

    protected SimpleOrderReq buildSimpleOrderReq() {
        String messageId = simpleOrderConfiguration.getSystemId() + DateTimeUtils.date2string()
                + System.currentTimeMillis();
        SimpleOrderReqBuilder b = new SimpleOrderReqBuilder(messageId);
        b.withClientId(simpleOrderConfiguration.getClientId()).withRequestTime(DateTimeUtils.datetime2string());
        b.withMessageType(MessageType.HeartBeatReq.ordinal());
        
        return b.build();
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SimpleOrderReq req = buildSimpleOrderReq();

        try {
            log.info("start heartbeat");
            CommonResponse resp = simpleOrderSender.send(req);
            log.info("" + resp);
            log.info("end heartbeat");

        } catch (ClientException e) {
            log.error("", e);
        }

    }

}
