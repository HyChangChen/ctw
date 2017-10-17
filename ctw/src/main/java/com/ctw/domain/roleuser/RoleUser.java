package com.ctw.domain.roleuser;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RoleUser implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;


	/** id */
    private Integer id;

	/** 用户ID */
    private Integer uid;
	/***用户名**/
	private String userName;

	/** 角色ID */
    private Integer roleId;

	/** 时间 */
    private java.util.Date ts;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return this.uid;
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
			.append("Uid",getUid())
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
		if(obj instanceof RoleUser == false) return false;
		if(this == obj) return true;
		RoleUser other = (RoleUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

