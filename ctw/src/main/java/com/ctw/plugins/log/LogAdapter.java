package com.ctw.plugins.log;

import java.util.HashMap;
import java.util.Map;

/**
 * ctw com.ctw.plugins.log
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/16 16: 44
 * @Version 1.0
 * @explain：............
 */
public class LogAdapter implements LogAPI {


    /**
     * @param message
     * @param logLevel
     * @see
     */
    @Override
    public void log(String message, LogLevel logLevel) {
        log(message, null, logLevel);
    }

    /**
     * @param message
     * @param objects
     * @param logLevel
     * @see
     */
    @Override
    public void log(String message, Object[] objects, LogLevel logLevel) {

    }

    /**
     * @return
     * @see
     */
    @Override
    public LogLevel getRootLogLevel() {
        return LogLevel.ERROR;
    }

    /**
     * @return
     * @see
     */
    @Override
    public Map<String, LogLevel> getCustomLogLevel() {
        return new HashMap<String, LogLevel>();
    }
}
