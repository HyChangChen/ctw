package com.ctw.domain.apps;

public class Apps implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	private String appsid;//appsid
	private String app;//app
	private String apptype;//apptype
	private String custapptype;//custapptype
	private Integer deletee;//deletee
	private String description;//description
	private String groupname;//groupname
	private Integer insertt;//insertt
	private Integer ismobile;//ismobile
	private String keyattribute;//keyattribute
	private String objectname;//objectname
	private String maxappsid;//maxappsid
	private String module;//module
	private String orderby;//orderby
	private String originalapp;//originalapp
	private Integer readd;//readd
	private String restrictions;//restrictions
	private Integer save;//save
	private String viewport;//viewport
	private String datasrc;//datasrc

	/**
	 * 自定义属性
	 */
	private String token;

	public void setAppsid(String appsid) {
		this.appsid = appsid;
	}

	public String getAppsid() {
		return this.appsid;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getApp() {
		return this.app;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getApptype() {
		return this.apptype;
	}

	public void setCustapptype(String custapptype) {
		this.custapptype = custapptype;
	}

	public String getCustapptype() {
		return this.custapptype;
	}

	public void setDeletee(Integer deletee) {
		this.deletee = deletee;
	}

	public Integer getDeletee() {
		return this.deletee;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setInsertt(Integer insertt) {
		this.insertt = insertt;
	}

	public Integer getInsertt() {
		return this.insertt;
	}

	public void setIsmobile(Integer ismobile) {
		this.ismobile = ismobile;
	}

	public Integer getIsmobile() {
		return this.ismobile;
	}

	public void setKeyattribute(String keyattribute) {
		this.keyattribute = keyattribute;
	}

	public String getKeyattribute() {
		return this.keyattribute;
	}

	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	public String getObjectname() {
		return this.objectname;
	}

	public void setMaxappsid(String maxappsid) {
		this.maxappsid = maxappsid;
	}

	public String getMaxappsid() {
		return this.maxappsid;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getModule() {
		return this.module;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getOrderby() {
		return this.orderby;
	}

	public void setOriginalapp(String originalapp) {
		this.originalapp = originalapp;
	}

	public String getOriginalapp() {
		return this.originalapp;
	}

	public void setReadd(Integer readd) {
		this.readd = readd;
	}

	public Integer getReadd() {
		return this.readd;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	public String getRestrictions() {
		return this.restrictions;
	}

	public void setSave(Integer save) {
		this.save = save;
	}

	public Integer getSave() {
		return this.save;
	}

	public void setViewport(String viewport) {
		this.viewport = viewport;
	}

	public String getViewport() {
		return this.viewport;
	}

	public void setDatasrc(String datasrc) {
		this.datasrc = datasrc;
	}

	public String getDatasrc() {
		return this.datasrc;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}