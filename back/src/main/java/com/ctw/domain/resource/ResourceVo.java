package com.ctw.domain.resource;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class ResourceVo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;


	/** id */
    private Integer id;

	/** 菜单名称 */
    private String name;

	/** url */
    private String url;

	/** 类型[menu button] */
    private String type;

	/** 顺序 */
    private Integer priority;

	/** parentId */
    private Integer parentId;

	/** 是否生效 */
    private Integer isValid;

	/** partentsIds */
    private String partentsIds;

	/** leaf */
    private String leaf="0";

	/** createNameId */
    private String createNameId;

	/** 创建时间 */
    private java.util.Date ts;

	/** 备注 */
    private String description;

	/** 样式 */
    private String icon;

	/** 权限 */
    private String permission;

    /**
     * html Val
     */
    private String htmlMenu;
    /*** 子节点**/
    private Integer hasChild;

    /**子节点集合*/
    private List<ResourceVo> children;
    public List<ResourceVo> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceVo> children) {
        this.children = children;
    }



    public String getHtmlMenu() {
        return htmlMenu;
    }

    public void setHtmlMenu(String htmlMenu) {
        this.htmlMenu = htmlMenu;
    }

    public Integer getHasChild() {
        return hasChild;
    }

    public void setHasChild(Integer hasChild) {
        this.hasChild = hasChild;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsValid() {
        return this.isValid;
    }

    public void setPartentsIds(String partentsIds) {
        this.partentsIds = partentsIds;
    }

    public String getPartentsIds() {
        return this.partentsIds;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getLeaf() {
        return this.leaf;
    }

    public void setCreateNameId(String createNameId) {
        this.createNameId = createNameId;
    }

    public String getCreateNameId() {
        return this.createNameId;
    }

    public void setTs(java.util.Date ts) {
        this.ts = ts;
    }

    public java.util.Date getTs() {
        return this.ts;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Url",getUrl())
			.append("Type",getType())
			.append("Priority",getPriority())
			.append("ParentId",getParentId())
			.append("IsValid",getIsValid())
			.append("PartentsIds",getPartentsIds())
			.append("Leaf",getLeaf())
			.append("CreateNameId",getCreateNameId())
			.append("Ts",getTs())
			.append("Description",getDescription())
			.append("Icon",getIcon())
			.append("Permission",getPermission())
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof ResourceVo == false) return false;
		if(this == obj) return true;
        ResourceVo other = (ResourceVo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

