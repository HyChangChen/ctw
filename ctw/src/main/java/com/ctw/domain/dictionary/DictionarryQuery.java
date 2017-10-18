package com.ctw.domain.dictionary;

import com.ctw.domain.common.PageQuery;

public class DictionarryQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;

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
    private  String typeVal;

    public String getTypeVal() {
        return typeVal;
    }

    public void setTypeVal(String typeVal) {
        this.typeVal = typeVal;
    }
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
    /**
     * 自定义属性
     */
    private String changedateRange;//创建时间范围

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public java.util.Date getChangedate() {
        return this.changedate;
    }

    public void setChangedate(java.util.Date changedate) {
        this.changedate = changedate;
    }

    public String getChangeby() {
        return this.changeby;
    }

    public void setChangeby(String changeby) {
        this.changeby = changeby;
    }

    public String getCurrencyid() {
        return this.currencyid;
    }

    public void setCurrencyid(String currencyid) {
        this.currencyid = currencyid;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getChangedateRange() {
        return this.changedateRange;
    }

    public void setChangedateRange(String changedateRange) {
        this.changedateRange = changedateRange;
    }

}