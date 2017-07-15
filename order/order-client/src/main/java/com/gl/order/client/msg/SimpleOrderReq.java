package com.gl.order.client.msg;

import com.gl.order.client.pojo.SimpleOrder;
import com.gl.order.common.msg.BaseRequest;

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
                + ", requestTime=" + requestTime + ", messageType=" + messageType + "]";
    }

}
