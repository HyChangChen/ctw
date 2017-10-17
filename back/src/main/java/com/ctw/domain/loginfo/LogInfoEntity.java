package com.ctw.domain.loginfo;

/**
 * t_sys_log_info
 */
public class LogInfoEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	private Long id;//id db_column: id
	private String functionName;//functionName db_column: function_name
	private String params;//params db_column: params
	private String contimes;//contimes db_column: contimes
	private String ipAddress;//ipAddress db_column: ip_address
	private String macAddress;//macAddress db_column: mac_address
	private String message;//message db_column: message
	private String username;//username db_column: username
	private String logLevel;//logLevel db_column: log_level
	private java.util.Date createTime;//createTime db_column: create_time

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getParams() {
		return this.params;
	}

	public void setContimes(String contimes) {
		this.contimes = contimes;
	}

	public String getContimes() {
		return this.contimes;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogLevel() {
		return this.logLevel;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

}
