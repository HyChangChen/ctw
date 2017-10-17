package com.ctw.domain.post;

import javax.validation.constraints.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * t_sys_post
 */
public class PostEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	/** id db_column: id */
    private Integer id;

	/** 岗位名称 db_column: post_name */
    private String postName;

	/** 岗位说明 db_column: description */
    private String description;

	/** 是否生效 db_column: is_valid */
    private Integer isValid;

	/** 样式 db_column: icon */
    private String icon;

	/** creatDate db_column: creat_date */
    private java.util.Date creatDate;

	/** 创建人ID db_column: create_name_id */
    private String createNameId;

	/** 所属组织 db_column: org_Id */
    private Integer orgId;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostName() {
        return this.postName;
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

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setCreatDate(java.util.Date creatDate) {
        this.creatDate = creatDate;
    }

    public java.util.Date getCreatDate() {
        return this.creatDate;
    }

    public void setCreateNameId(String createNameId) {
        this.createNameId = createNameId;
    }

    public String getCreateNameId() {
        return this.createNameId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOrgId() {
        return this.orgId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("PostName",getPostName())
			.append("Description",getDescription())
			.append("IsValid",getIsValid())
			.append("Icon",getIcon())
			.append("CreatDate",getCreatDate())
			.append("CreateNameId",getCreateNameId())
			.append("OrgId",getOrgId())
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof PostEntity == false) return false;
		if(this == obj) return true;
		PostEntity other = (PostEntity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

