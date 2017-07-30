package com.gl.order.frontend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gl.order.online.exception.OnlineException;
import com.gl.order.online.inter.SimpleOrderOnlineService;
import com.gl.order.online.vo.SimpleOrderVO;

@Service("simpleOrderOnlineService")
public class LocalSimpleOrderOnlineService implements SimpleOrderOnlineService {
    private static final Logger log = LoggerFactory.getLogger(LocalSimpleOrderOnlineService.class);

    @Override
    public SimpleOrderVO submit(SimpleOrderVO reqOrder) throws OnlineException {
        
        log.info(reqOrder.toString());
        return reqOrder;
    }

}
