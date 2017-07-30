package com.gl.order.client.batch.simpleorder.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.gl.order.client.batch.config.SimpleOrderConfiguration;
import com.gl.order.client.batch.exception.OrderJobExecutionException;
import com.gl.order.client.builder.SimpleOrderBuilder;
import com.gl.order.client.builder.SimpleOrderReqBuilder;
import com.gl.order.client.exception.ClientException;
import com.gl.order.client.msg.SimpleOrderReq;
import com.gl.order.client.pojo.SimpleOrder;
import com.gl.order.client.sender.SimpleOrderSender;
import com.gl.order.common.dict.MessageType;
import com.gl.order.common.dict.OrderStatus;
import com.gl.order.common.dict.OrderType;
import com.gl.order.common.msg.CommonResponse;
import com.gl.order.common.util.DateTimeUtils;

public class SimpleOrderJobEx extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(SimpleOrderJobEx.class);

    private SimpleOrderSender simpleOrderSender;

    private SimpleOrderConfiguration simpleOrderConfiguration = new SimpleOrderConfiguration();

    public static final String K_APPLICATION_CONTEXT = "K_APPLICATION_CONTEXT";

    public void executeInternal() throws OrderJobExecutionException {
        SimpleOrderReq req = buildSimpleOrderReq();

        try {
            log.info("start");
            CommonResponse resp = simpleOrderSender.send(req);
            log.info("" + resp);
            log.info("end");

        } catch (ClientException e) {
            log.error("", e);
            throw new OrderJobExecutionException(e);
        }
    }

    protected SimpleOrderReq buildSimpleOrderReq() {
        String messageId = simpleOrderConfiguration.getSystemId() + DateTimeUtils.date2string()
                + System.currentTimeMillis();
        SimpleOrderReqBuilder b = new SimpleOrderReqBuilder(messageId);
        b.withClientId(simpleOrderConfiguration.getClientId()).withRequestTime(DateTimeUtils.datetime2string());

        b.withMessageType(MessageType.NormalReq.ordinal());
        SimpleOrder order = buildSimpleOrder();
        b.withOrder(order);

        return b.build();
    }

    protected SimpleOrder buildSimpleOrder() {
        String orderId = DateTimeUtils.date2string() + System.currentTimeMillis();
        String bizId = simpleOrderConfiguration.getSystemId() + DateTimeUtils.date2string()
                + System.currentTimeMillis();
        String osName = System.getProperty("os.name") + "-" + System.getProperty("os.version");
        SimpleOrderBuilder b = new SimpleOrderBuilder(orderId);
        b.withBizId(bizId);
        b.withOrderType(OrderType.NORMAL.ordinal());
        b.withCreateTime(DateTimeUtils.datetime2string());
        b.withProductName(osName);
        b.withStatus(OrderStatus.New.getStatusName());

        return b.build();
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        try {
            ApplicationContext applicationCtx = (ApplicationContext) context.getScheduler().getContext()
                    .get(K_APPLICATION_CONTEXT);

            simpleOrderSender = applicationCtx.getBean("simpleOrderSender", SimpleOrderSender.class);
            simpleOrderConfiguration = applicationCtx.getBean("simpleOrderConfiguration",
                    SimpleOrderConfiguration.class);
            log.info("******" + simpleOrderSender);
            executeInternal();
        } catch (OrderJobExecutionException e) {
            log.error("", e);
            throw new JobExecutionException(e);
        } catch (SchedulerException e) {
            log.error("", e);
            throw new JobExecutionException(e);
        }

    }
}
