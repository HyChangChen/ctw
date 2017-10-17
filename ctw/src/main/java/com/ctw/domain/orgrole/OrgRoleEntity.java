package com.ctw.domain.orgrole;

import javax.validation.constraints.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * t_sys_org_role
 */
public class OrgRoleEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	/** id db_column: id */
    private Integer id;

	/** 岗位ID db_column: org_id */
    private Integer orgId;

	/** 角色Id db_column: role_id */
    private Integer roleId;

	/** ts db_column: ts */
    private java.util.Date ts;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOrgId() {
        return this.orgId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setTs(java.util.Date ts) {
        this.ts = ts;
    }

    public java.util.Date getTs() {
        return this.ts;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OrgId",getOrgId())
			.append("RoleId",getRoleId())
			.append("Ts",getTs())
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof OrgRoleEntity == false) return false;
		if(this == obj) return true;
		OrgRoleEntity other = (OrgRoleEntity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

