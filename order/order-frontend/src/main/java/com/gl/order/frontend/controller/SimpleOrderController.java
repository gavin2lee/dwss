package com.gl.order.frontend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public SimpleOrderResp order(SimpleOrderReq req) {
		log.info(String.format("<<< Server RECV:%s", req));
		SimpleOrderResp resp = null;
		SimpleOrder orderReq = req.getOrder();
		try {
			SimpleOrder orderRet = simpleOrderService.order(orderReq);
			resp = helper.buildResp(orderRet);
		} catch (OrderException e) {
			log.error("", e);
			resp = helper.buildAbnormalResp(orderReq, e);
		}

		log.info(String.format(">>> Server SEND:%s", resp));
		return resp;
	}

}