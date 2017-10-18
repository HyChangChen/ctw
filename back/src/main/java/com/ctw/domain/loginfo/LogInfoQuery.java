package com.ctw.domain.loginfo;

import com.ctw.domain.common.PageQuery;

public class LogInfoQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;

    private Long id;//id
    private String functionName;//functionName
    private String params;//params
    private String contimes;//contimes
    private String ipAddress;//ipAddress
    private String macAddress;//macAddress
    private String message;//message
    private String username;//username
    private String logLevel;//logLevel
    private java.util.Date createTime;//createTime

    /**
     * 自定义属性
     */
    private String createTimeRange;//createTime范围

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctionName() {
        return this.functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getContimes() {
        return this.contimes;
    }

    public void setContimes(String contimes) {
        this.contimes = contimes;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeRange() {
        return this.createTimeRange;
    }

    public void setCreateTimeRange(String createTimeRange) {
        this.createTimeRange = createTimeRange;
    }

}