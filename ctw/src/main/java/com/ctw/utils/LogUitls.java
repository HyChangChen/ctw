package com.ctw.utils;

import com.ctw.plugins.log.LogMessageObject;
import com.ctw.type.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * ctw com.ctw.utils
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/16 16: 22
 * @Version 1.0
 * @explain：............
 */
public abstract class LogUitls {
    // 用于存储每个线程的request请求
    private static final ThreadLocal<HttpServletRequest> LOCAL_REQUEST = new ThreadLocal<HttpServletRequest>();

    public static void putRequest(HttpServletRequest request) {
        LOCAL_REQUEST.set(request);
    }

    public static HttpServletRequest getRequest() {
        return LOCAL_REQUEST.get();
    }

    public static void removeRequest() {
        LOCAL_REQUEST.remove();
    }

    /**
     * 将LogMessageObject放入LOG_ARGUMENTS。
     * 描述
     * @param logMessageObject
     */
    public static void putArgs(LogMessageObject logMessageObject) {
        HttpServletRequest request = getRequest();
        request.setAttribute(Constants.LOG_ARGUMENTS, logMessageObject);
    }

    /**
     * 得到LogMessageObject。
     * 描述
     */
    public static LogMessageObject getArgs() {
        HttpServletRequest request = getRequest();
        return (LogMessageObject)request.getAttribute(Constants.LOG_ARGUMENTS);
    }
}
