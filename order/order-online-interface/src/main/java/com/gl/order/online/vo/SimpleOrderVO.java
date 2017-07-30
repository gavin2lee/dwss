package com.gl.order.online.vo;

public class SimpleOrderVO {
    /*
     * YYYYMMDD(8digits)+timestamp
     */
    protected String orderId;
    protected String createTime;
    protected String updateTime;
    /*
     * 
     * original systemId(2digits)+YYYYMMDD(8digits)+timestamp
     */
    protected String bizId;
    private int orderType;
    private String status;
    private String productName;
    private double amt;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

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
        return "SimpleOrderVO [orderId=" + orderId + ", createTime=" + createTime + ", updateTime=" + updateTime
                + ", bizId=" + bizId + ", orderType=" + orderType + ", status=" + status + ", productName="
                + productName + ", amt=" + amt + "]";
    }

    
}
