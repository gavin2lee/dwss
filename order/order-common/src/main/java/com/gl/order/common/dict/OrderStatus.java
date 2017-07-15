package com.gl.order.common.dict;

public enum OrderStatus {
    Normal("NOR");

    private final String statusName;

    private OrderStatus(String val) {
        this.statusName = val;
    }

    public String getStatusName() {
        return statusName;
    }

}
