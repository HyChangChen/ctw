package com.ctw.plugins.log;

/**
 * ctw com.ctw.plugins.aop
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/16 16: 10
 * @Version 1.0
 * @explain：............
 */

public enum LogLevel {
    TRACE("TRACE"),

    DEBUG("DEBUG"),

    INFO("INFO"),

    WARN("WARN"),

    ERROR("ERROR");

    private String value;

    LogLevel(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
