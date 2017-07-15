package com.gl.order.common.msg;

public interface CommonRequest extends CommonMessage {
    /*
     * C+3digits,i.e,C100,C101
     */
    String getClientId();

    /*
     * The time when this object was created YYYYMMDDHHmmssSSS
     */
    String getRequestTime();
}
