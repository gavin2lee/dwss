package com.gl.order.frontend.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.order.common.dict.MessageType;
import com.gl.order.common.dict.RetCodeMsgDict;
import com.gl.order.common.util.DateTimeUtils;
import com.gl.order.frontend.config.SysConfiguration;
import com.gl.order.frontend.msg.SimpleOrderReq;
import com.gl.order.frontend.msg.SimpleOrderResp;
import com.gl.order.frontend.pojo.SimpleOrder;

@Component
public class SimpleOrderControllerHelper {

    @Autowired
    private MessageInfoGenerator msgInfoGenerator;

    public SimpleOrderResp buildResp(SimpleOrder order) {
        SimpleOrderResp resp = new SimpleOrderResp();

        resp.setMessageId(getMessageId());
        resp.setServerId(getServerId());
        resp.setResponseTime(getResponseTime());

        resp.setRetCode(RetCodeMsgDict.CODE_SUCC);
        resp.setRetMsg(RetCodeMsgDict.MSG_SUCC);
        return resp;
    }

    public SimpleOrderResp buildAbnormalResp(SimpleOrder order, Exception e) {
        return null;
    }

    public SimpleOrderResp buildHeartBeatResp(SimpleOrderReq req) {
        SimpleOrderResp resp = new SimpleOrderResp();
        resp.setMessageType(MessageType.HeartBeatResp.ordinal());
        resp.setRetCode("0000");
        resp.setResponseTime(DateTimeUtils.datetime2string());
        resp.setServerId(getServerId());
        return resp;
    }

    private String getResponseTime() {
        return msgInfoGenerator.generateRespTime();
    }

    private String getServerId() {
        String frontendId = System.getProperty(SysConfiguration.DYNAMIC_PROP_SERVER_ID,
                SysConfiguration.DEFAULT_SERVER_ID);
        return frontendId;
    }

    private String getMessageId() {
        return msgInfoGenerator.generateMsgId();
    }
}
