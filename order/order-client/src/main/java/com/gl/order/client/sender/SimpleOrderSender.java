package com.gl.order.client.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gl.order.client.exception.ClientException;
import com.gl.order.client.msg.SimpleOrderResp;
import com.gl.order.common.msg.CommonRequest;
import com.gl.order.common.msg.CommonResponse;

public class SimpleOrderSender {
    private static final Logger log = LoggerFactory.getLogger(SimpleOrderSender.class);

    private HttpRemoteServer httpRemoteServer;

    private String context = "/order/simpleorders";

    private String heartbeatContext = "/order/heartbeats";

    public CommonResponse send(CommonRequest request) throws ClientException {
        log.info(String.format("SEND>>>%s", request));
        String body = JSONObject.toJSONString(request);
        String contextPath = (request.getMessageType() == CommonRequest.HEARTBEAT_REQ_MSG ? heartbeatContext : context);
        String respBody = httpRemoteServer.post(body, contextPath);

        SimpleOrderResp orderResp = JSON.parseObject(respBody, SimpleOrderResp.class);
        log.info(String.format("RECV<<<%s", orderResp));

        return orderResp;
    }

    public void setHttpRemoteServer(HttpRemoteServer httpRemoteServer) {
        this.httpRemoteServer = httpRemoteServer;
    }

    public void setContext(String context) {
        this.context = context;
    }

}
