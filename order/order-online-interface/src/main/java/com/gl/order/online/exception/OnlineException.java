package com.gl.order.online.exception;

public class OnlineException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public OnlineException() {
        super();
    }

    public OnlineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OnlineException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnlineException(String message) {
        super(message);
    }

    public OnlineException(Throwable cause) {
        super(cause);
    }
    
    

}
