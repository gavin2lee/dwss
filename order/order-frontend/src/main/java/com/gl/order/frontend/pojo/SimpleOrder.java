package com.gl.order.frontend.pojo;

import com.gl.order.common.pojo.BaseOrder;

public class SimpleOrder extends BaseOrder {
    private int orderType;
    private String status;
    private String productName;
    private double amt;

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    @Override
    public String toString() {
        return "SimpleOrder [orderType=" + orderType + ", status=" + status + ", productName=" + productName + ", amt="
                + amt + ", orderId=" + orderId + ", createTime=" + createTime + ", updateTime=" + updateTime
                + ", bizId=" + bizId + "]";
    }

}
