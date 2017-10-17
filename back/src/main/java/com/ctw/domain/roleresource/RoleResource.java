package com.ctw.domain.roleresource;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RoleResource implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;


	/** id */
    private Integer id;

	/** 角色ID */
    private Integer roleId;

	/** 资源ID */
    private Integer resourceId;

	/** ts */
    private java.util.Date ts;



	public RoleResource(){
	}

	public RoleResource(
		Integer id
	){
		this.id = id;
	}


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getResourceId() {
        return this.resourceId;
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
			.append("RoleId",getRoleId())
			.append("ResourceId",getResourceId())
			.append("Ts",getTs())
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof RoleResource == false) return false;
		if(this == obj) return true;
		RoleResource other = (RoleResource)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

