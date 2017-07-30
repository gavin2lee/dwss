package com.gl.order.frontend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gl.order.frontend.exception.OrderException;
import com.gl.order.frontend.pojo.SimpleOrder;
import com.gl.order.online.exception.OnlineException;
import com.gl.order.online.inter.SimpleOrderOnlineService;
import com.gl.order.online.vo.SimpleOrderVO;

@Service
public class SimpleOrderService {
    private static final Logger log = LoggerFactory.getLogger(SimpleOrderService.class);

    @Autowired
    @Qualifier("simpleOrderOnlineService")
    private SimpleOrderOnlineService simpleOrderOnlineService;

    public SimpleOrder order(SimpleOrder order) throws OrderException {
        log.info(String.format("%s", order));

        try {
            SimpleOrderVO vo = simpleOrderOnlineService.submit(convert(order));
            return convert(vo);
        } catch (OnlineException e) {
            throw new OrderException(e);
        }

    }

    private SimpleOrderVO convert(SimpleOrder o) {
        SimpleOrderVO vo = new SimpleOrderVO();
        vo.setAmt(o.getAmt());
        vo.setBizId(o.getBizId());
        vo.setCreateTime(o.getCreateTime());
        vo.setOrderId(o.getOrderId());
        vo.setOrderType(o.getOrderType());
        vo.setProductName(o.getProductName());
        vo.setStatus(o.getStatus());
        vo.setUpdateTime(o.getUpdateTime());

        return vo;
    }

    private SimpleOrder convert(SimpleOrderVO vo) {
        SimpleOrder o = new SimpleOrder();
        o.setAmt(vo.getAmt());
        o.setBizId(vo.getBizId());
        o.setCreateTime(vo.getCreateTime());
        o.setOrderId(vo.getOrderId());
        o.setOrderType(vo.getOrderType());
        o.setProductName(vo.getProductName());
        o.setStatus(vo.getStatus());
        o.setUpdateTime(vo.getUpdateTime());

        return o;
    }
}
