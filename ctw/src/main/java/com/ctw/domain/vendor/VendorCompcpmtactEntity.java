package com.ctw.domain.vendor;

/**
 * t_bus_vendor_compcpmtact
 */
public class VendorCompcpmtactEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	private String id;//id db_column: id
	private String compcontactnum;//联系人编号 db_column: compcontactnum
	private String vendorId;//所属供应商 db_column: vendor_id
	private String compcontactid;//公司联系人标识 db_column: compcontactid
	private String company;//公司 db_column: company
	private String contact;//联系人 db_column: contact
	private String position;//职位 db_column: position
	private String voicePhone;//办公电话 db_column: voice_phone
	private String faxPhone;//传真 db_column: fax_phone
	private String email;//邮箱 db_column: email
	private String homePhone;//家庭电话 db_column: home_phone
	private String cellPhone;//移动电话 db_column: cell_phone
	private String vendorNum;//Vendor_num db_column: vendor_num
	private String description;//备注 db_column: description
	private String status;//状态 db_column: status
	private String orgid;//ORGID db_column: ORGID
	private String siteid;//SITEID db_column: SITEID

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

}
