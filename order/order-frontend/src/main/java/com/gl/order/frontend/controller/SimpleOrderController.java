package com.gl.order.frontend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.order.common.dict.MessageType;
import com.gl.order.frontend.exception.OrderException;
import com.gl.order.frontend.helper.SimpleOrderControllerHelper;
import com.gl.order.frontend.msg.SimpleOrderReq;
import com.gl.order.frontend.msg.SimpleOrderResp;
import com.gl.order.frontend.pojo.SimpleOrder;
import com.gl.order.frontend.service.SimpleOrderService;

@RestController
public class SimpleOrderController {
    private static final Logger log = LoggerFactory.getLogger(SimpleOrderController.class);

    @Autowired
    private SimpleOrderService simpleOrderService;

    @Autowired
    private SimpleOrderControllerHelper helper;

    @PostMapping(path = "/simpleorders")
    public SimpleOrderResp createOrder(@RequestBody SimpleOrderReq req) {
        log.info(String.format("<<< RECV:%s", req));
        SimpleOrderResp resp = null;

        resp = processCreateOrder(req);

        log.info(String.format(">>> SEND:%s", resp));
        return resp;
    }

    @PostMapping(path = "/heartbeats")
    public SimpleOrderResp heartbeat(@RequestBody SimpleOrderReq req) {
        log.info(String.format("<<< HeartBeat RECV:%s", req));

        SimpleOrderResp resp = null;

        if (req.getMessageType() == MessageType.HeartBeatReq.ordinal()) {
            resp = processHeartBeatOrder(req);
        }

        log.info(String.format(">>> HeartBeat SEND:%s", resp));

        return resp;
    }

    protected SimpleOrderResp processCreateOrder(SimpleOrderReq req) {
        log.info("***********   create order st *********");
        SimpleOrder orderReq = req.getOrder();
        SimpleOrderResp resp = null;
        try {
            SimpleOrder orderRet = simpleOrderService.order(orderReq);
            resp = helper.buildResp(orderRet);
        } catch (OrderException e) {
            log.error("", e);
            resp = helper.buildAbnormalResp(orderReq, e);
        }

        log.info("***********   create order ed *********");
        return resp;
    }

    protected SimpleOrderResp processHeartBeatOrder(SimpleOrderReq req) {
        SimpleOrderResp resp = helper.buildHeartBeatResp(req);

        return resp;
    }
}
