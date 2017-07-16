package com.gl.order.client.batch.simpleorder.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl.order.client.batch.config.SimpleOrderConfiguration;
import com.gl.order.client.batch.exception.OrderJobExecutionException;
import com.gl.order.client.builder.SimpleOrderReqBuilder;
import com.gl.order.client.exception.ClientException;
import com.gl.order.client.msg.SimpleOrderReq;
import com.gl.order.client.sender.SimpleOrderSender;
import com.gl.order.common.dict.MessageType;
import com.gl.order.common.msg.CommonResponse;
import com.gl.order.common.util.DateTimeUtils;

public class SimpleOrderHeartBeatJob {
    private static final Logger log = LoggerFactory.getLogger(SimpleOrderHeartBeatJob.class);

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

    public void executeInternal() throws OrderJobExecutionException {
        SimpleOrderReq req = buildSimpleOrderReq();

        try {
            log.info("start heartbeat");
            CommonResponse resp = simpleOrderSender.send(req);
            log.info("" + resp);
            log.info("end heartbeat");

        } catch (ClientException e) {
            log.error("", e);
            throw new OrderJobExecutionException(e);
        }

    }

    public void setSimpleOrderSender(SimpleOrderSender simpleOrderSender) {
        this.simpleOrderSender = simpleOrderSender;
    }

    public void setSimpleOrderConfiguration(SimpleOrderConfiguration simpleOrderConfiguration) {
        this.simpleOrderConfiguration = simpleOrderConfiguration;
    }

    public SimpleOrderSender getSimpleOrderSender() {
        return simpleOrderSender;
    }

    public SimpleOrderConfiguration getSimpleOrderConfiguration() {
        return simpleOrderConfiguration;
    }

}
