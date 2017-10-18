package com.ctw.domain.resource;

import com.ctw.domain.common.PageQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class ResourceQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;


    /**
     * id
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     * 类型[menu button]
     */
    private String type = "menu";

    /**
     * 顺序
     */
    private Integer priority;

    /**
     * parentId
     */
    private Integer parentId;

    /**
     * 是否生效
     */
    private Integer isValid = 1;

    /**
     * partentsIds
     */
    private String partentsIds;

    /**
     * leaf
     */
    private String leaf;

    /**
     * createNameId
     */
    private String createNameId;

    /**
     * 创建时间
     */
    private java.util.Date ts;

    /**
     * 创建时间Begin
     */
    private java.util.Date tsBegin;

    /**
     * 创建时间End
     */
    private java.util.Date tsEnd;

    /**
     * 备注
     */
    private String description;

    /**
     * 样式
     */
    private String icon;

    /**
     * 权限
     */
    private String permission;

    /*角色ID 根据角色查询该角色拥有的资源*/
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    private List<Integer> resourceIds;

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public ResourceQuery() {
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

    public void setTsBegin(java.util.Date tsBegin) {
        this.tsBegin = tsBegin;
    }

    public java.util.Date getTsBegin() {
        return this.tsBegin;
    }

    public void setTsEnd(java.util.Date tsEnd) {
        this.tsEnd = tsEnd;
    }

    public java.util.Date getTsEnd() {
        return this.tsEnd;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("Name", getName())
                .append("Url", getUrl())
                .append("Type", getType())
                .append("Priority", getPriority())
                .append("ParentId", getParentId())
                .append("IsValid", getIsValid())
                .append("PartentsIds", getPartentsIds())
                .append("Leaf", getLeaf())
                .append("CreateNameId", getCreateNameId())
                .append("Ts", getTs())
                .append("TsBegin", getTsBegin())
                .append("TsEnd", getTsEnd())
                .append("Description", getDescription())
                .append("Icon", getIcon())
                .append("Permission", getPermission())
                .toString();
    }

}