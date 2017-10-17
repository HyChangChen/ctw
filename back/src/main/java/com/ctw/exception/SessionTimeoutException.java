package com.ctw.exception;

/**
 * ctw com.ctw.exception
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/16 01: 16
 * @Version 1.0
 * @explain：............
 */
public class SessionTimeoutException extends Exception{

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 5721686959716896576L;

    public SessionTimeoutException() {

    }

    public SessionTimeoutException(Throwable cause) {
        super(cause);
    }

    public SessionTimeoutException(String message) {
        super(message);
    }
}
