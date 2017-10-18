package com.ctw.domain.role;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Role implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;


	/** id */
    private Integer id;

	/** 角色名称 */
    private String roleName;

	/** 备注 */
    private String description;

	/** 是否生效 */
    private Integer isValid;

	/** 创建时间 */
    private java.util.Date createDate;

	/** 创建人 */
    private Integer createNameId;
    /**来源**/
    private String fromSource;

    public String getFromSource() {
        return fromSource;
    }

    public void setFromSource(String fromSource) {
        this.fromSource = fromSource;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsValid() {
        return this.isValid;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateNameId(Integer createNameId) {
        this.createNameId = createNameId;
    }

    public Integer getCreateNameId() {
        return this.createNameId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("RoleName",getRoleName())
			.append("Description",getDescription())
			.append("IsValid",getIsValid())
			.append("CreateDate",getCreateDate())
			.append("CreateNameId",getCreateNameId())
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof Role == false) return false;
		if(this == obj) return true;
		Role other = (Role)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

