package com.ctw.domain.vendor;

/**
 * t_bus_vendor
 */
public class VendorEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	private String id;//ID db_column: id
	private String vendorNum;//编号 db_column: vendor_num
	private String vendorName;//名称 db_column: vendor_name
	private String type;//公司类型 db_column: type
	private String contact;//公司法人 db_column: contact
	private String phone;//法人电话号码 db_column: phone
	private String address;//公司地址 db_column: address
	private String paymentTerms;//支付条款 db_column: payment_terms
	private String remitContact;//注册资金 db_column: remit_contact
	private String statuss;//状态 db_column: statuss
	private Integer changeby;//创建人 db_column: changeby
	private java.util.Date datetime;//创建时间 db_column: datetime
	private String bankaccount;//银行账户 db_column: BANKACCOUNT
	private String fax;//传真 db_column: fax
	private String currencyCode;//货币类型 db_column: currency_code
	private String shipvia;//运输方式 db_column: shipvia
	private String taxrate;//税率 db_column: taxrate
	private String note;//备注 db_column: note
	private String remitaDdress;//收款人地址 db_column: remita_ddress
	private String regIstration;//税注册号码 db_column: reg_istration
	private String groupid;//集团 db_column: groupid
	private String openAccount;//开户行 db_column: open_account
	private String mail;//邮箱 db_column: mail
	private String scope;//经营范围 db_column: scope
	private String orgid;//ID db_column: ORGID
	private String siteid;//ID db_column: SITEID

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setVendorNum(String vendorNum) {
		this.vendorNum = vendorNum;
	}

	public String getVendorNum() {
		return this.vendorNum;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContact() {
		return this.contact;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getPaymentTerms() {
		return this.paymentTerms;
	}

	public void setRemitContact(String remitContact) {
		this.remitContact = remitContact;
	}

	public String getRemitContact() {
		return this.remitContact;
	}

	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}

	public String getStatuss() {
		return this.statuss;
	}

	public void setChangeby(Integer changeby) {
		this.changeby = changeby;
	}

	public Integer getChangeby() {
		return this.changeby;
	}

	public void setDatetime(java.util.Date datetime) {
		this.datetime = datetime;
	}

	public java.util.Date getDatetime() {
		return this.datetime;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getBankaccount() {
		return this.bankaccount;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return this.fax;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setShipvia(String shipvia) {
		this.shipvia = shipvia;
	}

	public String getShipvia() {
		return this.shipvia;
	}

	public void setTaxrate(String taxrate) {
		this.taxrate = taxrate;
	}

	public String getTaxrate() {
		return this.taxrate;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return this.note;
	}

	public void setRemitaDdress(String remitaDdress) {
		this.remitaDdress = remitaDdress;
	}

	public String getRemitaDdress() {
		return this.remitaDdress;
	}

	public void setRegIstration(String regIstration) {
		this.regIstration = regIstration;
	}

	public String getRegIstration() {
		return this.regIstration;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupid() {
		return this.groupid;
	}

	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}

	public String getOpenAccount() {
		return this.openAccount;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return this.mail;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getScope() {
		return this.scope;
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
