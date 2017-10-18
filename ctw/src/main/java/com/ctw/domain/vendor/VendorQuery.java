package com.ctw.domain.vendor;

import com.ctw.domain.common.PageQuery;

public class VendorQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;

    private String id;//ID
    private String vendorNum;//编号
    private String vendorName;//名称
    private String type;//公司类型
    private String contact;//公司法人
    private String phone;//法人电话号码
    private String address;//公司地址
    private String paymentTerms;//支付条款
    private String remitContact;//注册资金
    private String statuss;//状态
    private Integer changeby;//创建人
    private java.util.Date datetime;//创建时间
    private String bankaccount;//银行账户
    private String fax;//传真
    private String currencyCode;//货币类型
    private String shipvia;//运输方式
    private String taxrate;//税率
    private String note;//备注
    private String remitaDdress;//收款人地址
    private String regIstration;//税注册号码
    private String groupid;//集团
    private String openAccount;//开户行
    private String mail;//邮箱
    private String scope;//经营范围
    private String orgid;//ID
    private String siteid;//ID

    /**
     * 自定义属性
     */
    private String datetimeRange;//创建时间范围

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendorNum() {
        return this.vendorNum;
    }

    public void setVendorNum(String vendorNum) {
        this.vendorNum = vendorNum;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentTerms() {
        return this.paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getRemitContact() {
        return this.remitContact;
    }

    public void setRemitContact(String remitContact) {
        this.remitContact = remitContact;
    }

    public String getStatuss() {
        return this.statuss;
    }

    public void setStatuss(String statuss) {
        this.statuss = statuss;
    }

    public Integer getChangeby() {
        return this.changeby;
    }

    public void setChangeby(Integer changeby) {
        this.changeby = changeby;
    }

    public java.util.Date getDatetime() {
        return this.datetime;
    }

    public void setDatetime(java.util.Date datetime) {
        this.datetime = datetime;
    }

    public String getBankaccount() {
        return this.bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getShipvia() {
        return this.shipvia;
    }

    public void setShipvia(String shipvia) {
        this.shipvia = shipvia;
    }

    public String getTaxrate() {
        return this.taxrate;
    }

    public void setTaxrate(String taxrate) {
        this.taxrate = taxrate;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRemitaDdress() {
        return this.remitaDdress;
    }

    public void setRemitaDdress(String remitaDdress) {
        this.remitaDdress = remitaDdress;
    }

    public String getRegIstration() {
        return this.regIstration;
    }

    public void setRegIstration(String regIstration) {
        this.regIstration = regIstration;
    }

    public String getGroupid() {
        return this.groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getOpenAccount() {
        return this.openAccount;
    }

    public void setOpenAccount(String openAccount) {
        this.openAccount = openAccount;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getOrgid() {
        return this.orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getSiteid() {
        return this.siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getDatetimeRange() {
        return this.datetimeRange;
    }

    public void setDatetimeRange(String datetimeRange) {
        this.datetimeRange = datetimeRange;
    }

}