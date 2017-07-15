package com.gl.order.client.builder;

import com.gl.order.client.pojo.SimpleOrder;

public class SimpleOrderBuilder {
    /*
     * YYYYMMDD(8digits)+timestamp
     */
    private String orderId;
    private String createTime;
    private String updateTime;
    /*
     * 
     * original systemId(2digits)+YYYYMMDD(8digits)+timestamp
     */
    private String bizId;
    private int orderType;
    private String status;
    private String productName;
    private double amt;

    public SimpleOrderBuilder(String orderId) {
        this.orderId = orderId;
    }

    public SimpleOrderBuilder(SimpleOrder prototype) {
        orderId = prototype.getOrderId();
        createTime = prototype.getCreateTime();
        updateTime = prototype.getUpdateTime();
        bizId = prototype.getBizId();
        orderType = prototype.getOrderType();
        status = prototype.getStatus();
        productName = prototype.getProductName();
        amt = prototype.getAmt();
    }

    public SimpleOrderBuilder withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public SimpleOrderBuilder withCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public SimpleOrderBuilder withUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public SimpleOrderBuilder withBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    public SimpleOrderBuilder withOrderType(int orderType) {
        this.orderType = orderType;
        return this;
    }

    public SimpleOrderBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public SimpleOrderBuilder withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public SimpleOrderBuilder withAmt(double amt) {
        this.amt = amt;
        return this;
    }

    public SimpleOrder build() {
        SimpleOrder so = new SimpleOrder();
        so.setAmt(amt);
        so.setBizId(bizId);
        so.setCreateTime(createTime);
        so.setOrderId(orderId);
        so.setOrderType(orderType);
        so.setProductName(productName);
        so.setStatus(status);
        so.setUpdateTime(updateTime);

        return so;
    }
}
