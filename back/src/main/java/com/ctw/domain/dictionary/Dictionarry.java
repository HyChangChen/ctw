package com.ctw.domain.dictionary;

public class Dictionarry implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	private Integer id;//id
	private String remark;//备注

	private String description;//说明
	private String active;//状态
	private java.util.Date changedate;//创建时间
	private String changeby;//创建人
	private String currencyid;//currencyid
	private Integer type;//类型
	private String orgid;//orgid
	private String siteid;//siteid
	private String typeVal;
	/** 标签名 */
	private String text;

	/** 数据值 */
	private String value;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTypeVal() {
		return typeVal;
	}

	public void setTypeVal(String typeVal) {
		this.typeVal = typeVal;
	}

	/**
	 * 自定义属性
	 */
	private String token;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}



	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getActive() {
		return this.active;
	}

	public void setChangedate(java.util.Date changedate) {
		this.changedate = changedate;
	}

	public java.util.Date getChangedate() {
		return this.changedate;
	}

	public void setChangeby(String changeby) {
		this.changeby = changeby;
	}

	public String getChangeby() {
		return this.changeby;
	}

	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}

	public String getCurrencyid() {
		return this.currencyid;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public String getSiteid() {
		return this.siteid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}