package com.ctw.domain.org;

import javax.validation.constraints.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * t_sys_org
 */
public class OrgEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	/** id db_column: id */
    private Integer id;

	/** 组织名称 db_column: org_name */
    private String orgName;

	/** 备注 db_column: partent_id */
    private Integer partentId;

	/** 是否根节点 db_column: leaf */
    private Integer leaf;

	/** 是否生效 db_column: is_valid */
    private Integer isValid;

	/** ts db_column: ts */
    private java.util.Date ts;

	/** createNameId db_column: create_name_id */
    private Integer createNameId;

	/** 样式 db_column: icon */
    private String icon;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setPartentId(Integer partentId) {
        this.partentId = partentId;
    }

    public Integer getPartentId() {
        return this.partentId;
    }

    public void setLeaf(Integer leaf) {
        this.leaf = leaf;
    }

    public Integer getLeaf() {
        return this.leaf;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsValid() {
        return this.isValid;
    }

    public void setTs(java.util.Date ts) {
        this.ts = ts;
    }

    public java.util.Date getTs() {
        return this.ts;
    }

    public void setCreateNameId(Integer createNameId) {
        this.createNameId = createNameId;
    }

    public Integer getCreateNameId() {
        return this.createNameId;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OrgName",getOrgName())
			.append("PartentId",getPartentId())
			.append("Leaf",getLeaf())
			.append("IsValid",getIsValid())
			.append("Ts",getTs())
			.append("CreateNameId",getCreateNameId())
			.append("Icon",getIcon())
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof OrgEntity == false) return false;
		if(this == obj) return true;
		OrgEntity other = (OrgEntity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

