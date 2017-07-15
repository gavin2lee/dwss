package com.gl.order.frontend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gl.order.frontend.exception.OrderException;
import com.gl.order.frontend.pojo.SimpleOrder;

@Service
public class SimpleOrderService {
    private static final Logger log = LoggerFactory.getLogger(SimpleOrderService.class);

    public SimpleOrder order(SimpleOrder order) throws OrderException {
        log.info(String.format("%s", order));

        return null;
    }
}
