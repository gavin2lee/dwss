package com.gl.order.online.inter.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl.order.online.exception.OnlineException;
import com.gl.order.online.inter.SimpleOrderOnlineService;
import com.gl.order.online.vo.SimpleOrderVO;

public class SimpleOrderOnlineServiceProvider implements SimpleOrderOnlineService {
    private static final Logger log = LoggerFactory.getLogger(SimpleOrderOnlineServiceProvider.class);

    @Override
    public SimpleOrderVO submit(SimpleOrderVO reqOrder) throws OnlineException {
        return null;
    }

}
