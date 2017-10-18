package com.ctw.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * ctw com.ctw.exception
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/17 11: 33
 * @Version 1.0
 * @explain：............
 */
public class IncorrectCaptchaException extends AuthenticationException {
    /**
     * 描述
     */
    private static final long serialVersionUID = 6146451562810994591L;

    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }
}