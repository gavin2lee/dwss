package com.gl.order.frontend.msg;

import com.gl.order.common.msg.BaseRequest;
import com.gl.order.frontend.pojo.SimpleOrder;

public class SimpleOrderReq extends BaseRequest {
    private SimpleOrder order;

    public SimpleOrder getOrder() {
        return order;
    }

    public void setOrder(SimpleOrder order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "SimpleOrderReq [order=" + order + ", messageId=" + messageId + ", clientId=" + clientId
                + ", requestTime=" + requestTime + "]";
    }

}
