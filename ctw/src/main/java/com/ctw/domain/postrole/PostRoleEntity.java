package com.ctw.domain.postrole;

import javax.validation.constraints.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * t_sys_post_role
 */
public class PostRoleEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	/** id db_column: id */
    private Integer id;

	/** 岗位ID db_column: psot_id */
    private Integer psotId;

	/** 角色ID db_column: role_id */
    private Integer roleId;

	/** ts db_column: ts */
    private java.util.Date ts;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setPsotId(Integer psotId) {
        this.psotId = psotId;
    }

    public Integer getPsotId() {
        return this.psotId;
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
			.append("PsotId",getPsotId())
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
		if(obj instanceof PostRoleEntity == false) return false;
		if(this == obj) return true;
		PostRoleEntity other = (PostRoleEntity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

