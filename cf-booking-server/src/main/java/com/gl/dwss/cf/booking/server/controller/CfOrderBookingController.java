package com.gl.dwss.cf.booking.server.controller;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.dwss.cf.objects.pojo.CfOrder;

@RestController
public class CfOrderBookingController {
	private static final Logger LOG = LoggerFactory.getLogger(CfOrderBookingController.class);

	private Map<Long, CfOrder> cfOrders = new Hashtable<Long, CfOrder>();

	private AtomicLong cfOrderIdGenerator = new AtomicLong();

	@RequestMapping(path="/cfOrders", method={RequestMethod.POST})
	public void createCfOrder(@RequestBody CfOrder cfOrder) {
		Long oid = cfOrderIdGenerator.incrementAndGet();
		cfOrder.setOid(oid);

		cfOrders.put(oid, cfOrder);

		LOG.debug("createCfOrder:" + cfOrder);
	}
	
	
	@RequestMapping(path="/cfOrders/{oid}", method={RequestMethod.GET})
	public CfOrder findCfOrder(@PathVariable("oid") long oid){
		LOG.debug("findCfOrder:" + oid);
		return cfOrders.get(oid);
	}
}
