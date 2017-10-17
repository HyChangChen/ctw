package com.ctw.domain.vendor;

import com.ctw.domain.common.PageQuery;

public class VendorCompcpmtactQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;

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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompcontactnum() {
        return this.compcontactnum;
    }

    public void setCompcontactnum(String compcontactnum) {
        this.compcontactnum = compcontactnum;
    }

    public String getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getCompcontactid() {
        return this.compcontactid;
    }

    public void setCompcontactid(String compcontactid) {
        this.compcontactid = compcontactid;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVoicePhone() {
        return this.voicePhone;
    }

    public void setVoicePhone(String voicePhone) {
        this.voicePhone = voicePhone;
    }

    public String getFaxPhone() {
        return this.faxPhone;
    }

    public void setFaxPhone(String faxPhone) {
        this.faxPhone = faxPhone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhone() {
        return this.homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCellPhone() {
        return this.cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getVendorNum() {
        return this.vendorNum;
    }

    public void setVendorNum(String vendorNum) {
        this.vendorNum = vendorNum;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}