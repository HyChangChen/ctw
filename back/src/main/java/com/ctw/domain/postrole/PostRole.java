package com.ctw.domain.postrole;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PostRole implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;


	/** id */
    private Integer id;

	/** 岗位ID */
    private Integer psotId;

	/**岗位名称**/
	private String postName;



	/** 角色ID */
    private Integer roleId;

	/** ts */
    private java.util.Date ts;

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}


	public PostRole(){
	}

	public PostRole(
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
		if(obj instanceof PostRole == false) return false;
		if(this == obj) return true;
		PostRole other = (PostRole)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

