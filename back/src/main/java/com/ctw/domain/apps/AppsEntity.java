package com.ctw.domain.apps;

/**
 * apps
 */
public class AppsEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	private String appsid;//appsid db_column: appsid
	private String app;//app db_column: app
	private String apptype;//apptype db_column: apptype
	private String custapptype;//custapptype db_column: custapptype
	private Integer deletee;//deletee db_column: deletee
	private String description;//description db_column: description
	private String groupname;//groupname db_column: groupname
	private Integer insertt;//insertt db_column: insertt
	private Integer ismobile;//ismobile db_column: ismobile
	private String keyattribute;//keyattribute db_column: keyattribute
	private String objectname;//objectname db_column: objectname
	private String maxappsid;//maxappsid db_column: maxappsid
	private String module;//module db_column: module
	private String orderby;//orderby db_column: orderby
	private String originalapp;//originalapp db_column: originalapp
	private Integer readd;//readd db_column: readd
	private String restrictions;//restrictions db_column: restrictions
	private Integer save;//save db_column: save
	private String viewport;//viewport db_column: viewport
	private String datasrc;//datasrc db_column: datasrc

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

}
