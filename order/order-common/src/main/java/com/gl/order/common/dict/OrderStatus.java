package com.gl.order.common.dict;

public enum OrderStatus {
    Normal("NOR"), /**/
    New("NEW"), /**/
    Amend("AMEND"), /**/
    Cancel("CANCEL"), /**/
    Reject("REJECT"), /**/
    InProcess("INPROC"), /**/
    Succ("SUCC");/**/

    private final String statusName;

    private OrderStatus(String val) {
        this.statusName = val;
    }

    public String getStatusName() {
        return statusName;
    }

}
