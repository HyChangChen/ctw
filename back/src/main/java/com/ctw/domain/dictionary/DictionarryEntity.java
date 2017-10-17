package com.ctw.domain.dictionary;

/**
 * t_sys_data_dictionary
 */
public class DictionarryEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	private Integer id;//id db_column: id
	private String remark;//备注 db_column: remark
	private String description;//说明 db_column: description
	private String active;//状态 db_column: active
	private java.util.Date changedate;//创建时间 db_column: changedate
	private String changeby;//创建人 db_column: changeby
	private String currencyid;//currencyid db_column: currencyid
	private Integer type;//类型 db_column: type
	private String orgid;//orgid db_column: ORGID
	private String siteid;//siteid db_column: SITEID
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

}
