package com.ctw.plugins.log;

import java.util.Map;

/**
 * ctw com.ctw.plugins.log
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/16 16: 45
 * @Version 1.0
 * @explain：............
*/
public interface LogAPI {
    void log(String message, LogLevel logLevel);

    void log(String message, Object[] objects, LogLevel logLevel);

    /**
     *
     * 得到全局日志等级
     * @return
     */
    LogLevel getRootLogLevel();

    /**
     *
     * 得到自定义包的日志等级
     * @return
     */
    Map<String, LogLevel> getCustomLogLevel();



}
