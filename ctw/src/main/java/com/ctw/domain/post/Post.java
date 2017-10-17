package com.ctw.domain.post;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Post implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;


	/** id */
    private Integer id;

	/** 岗位名称 */
    private String postName;

	/** 岗位说明 */
    private String description;

	/** 是否生效 */
    private Integer isValid;

	/** 样式 */
    private String icon;

	/** creatDate */
    private java.util.Date creatDate;

	/** 创建人ID */
    private String createNameId;

	/** 所属组织 */
    private Integer orgId;
    private String orgName;
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

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
		if(obj instanceof Post == false) return false;
		if(this == obj) return true;
		Post other = (Post)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

