package com.ctw.domain.vendor;

public class VendorCompcpmtact implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	private String id;//id
	private String compcontactnum;//联系人编号
	private String vendorId;//所属供应商
	private String compcontactid;//公司联系人标识
	private String company;//公司
	private String contact;//联系人
	private String position;//职位
	private String voicePhone;//办公电话
	private String faxPhone;//传真
	private String email;//邮箱
	private String homePhone;//家庭电话
	private String cellPhone;//移动电话
	private String vendorNum;//Vendor_num
	private String description;//备注
	private String status;//状态
	private String orgid;//ORGID
	private String siteid;//SITEID

	/**
	 * 自定义属性
	 */
	private String token;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setCompcontactnum(String compcontactnum) {
		this.compcontactnum = compcontactnum;
	}

	public String getCompcontactnum() {
		return this.compcontactnum;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorId() {
		return this.vendorId;
	}

	public void setCompcontactid(String compcontactid) {
		this.compcontactid = compcontactid;
	}

	public String getCompcontactid() {
		return this.compcontactid;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return this.company;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContact() {
		return this.contact;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return this.position;
	}

	public void setVoicePhone(String voicePhone) {
		this.voicePhone = voicePhone;
	}

	public String getVoicePhone() {
		return this.voicePhone;
	}

	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}

	public String getFaxPhone() {
		return this.faxPhone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setVendorNum(String vendorNum) {
		this.vendorNum = vendorNum;
	}

	public String getVendorNum() {
		return this.vendorNum;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
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