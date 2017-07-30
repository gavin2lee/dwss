package com.gl.order.online.inter.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl.order.common.dict.OrderStatus;
import com.gl.order.common.util.DateTimeUtils;
import com.gl.order.online.exception.OnlineException;
import com.gl.order.online.inter.SimpleOrderOnlineService;
import com.gl.order.online.vo.SimpleOrderVO;

public class SimpleOrderOnlineServiceProvider implements SimpleOrderOnlineService {
    private static final Logger log = LoggerFactory.getLogger(SimpleOrderOnlineServiceProvider.class);

    @Override
    public SimpleOrderVO submit(SimpleOrderVO reqOrder) throws OnlineException {
        log.info(String.format("submit:%s %s %s",reqOrder.getBizId(), reqOrder.getStatus(), reqOrder.getAmt()));
        reqOrder.setStatus(OrderStatus.InProcess.getStatusName());
        reqOrder.setUpdateTime(DateTimeUtils.datetime2string());
        return reqOrder;
    }

}
