package com.ctw.domain.post;

import com.ctw.domain.common.PageQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class PostQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;


    /**
     * id
     */
    private Integer id;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 岗位说明
     */
    private String description;

    /**
     * 是否生效
     */
    private Integer isValid = 1;

    /**
     * 样式
     */
    private String icon;

    /**
     * creatDate
     */
    private java.util.Date creatDate;

    /**
     * creatDateBegin
     */
    private java.util.Date creatDateBegin;

    /**
     * creatDateEnd
     */
    private java.util.Date creatDateEnd;

    /**
     * 创建人ID
     */
    private String createNameId;

    /**
     * 所属组织
     */
    private Integer orgId;
    private String orgName;

    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    private List<Integer> postIds;

    public List<Integer> getPostIds() {
        return postIds;
    }

    public void setPostIds(List<Integer> postIds) {
        this.postIds = postIds;
    }


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

    public void setCreatDateBegin(java.util.Date creatDateBegin) {
        this.creatDateBegin = creatDateBegin;
    }

    public java.util.Date getCreatDateBegin() {
        return this.creatDateBegin;
    }

    public void setCreatDateEnd(java.util.Date creatDateEnd) {
        this.creatDateEnd = creatDateEnd;
    }

    public java.util.Date getCreatDateEnd() {
        return this.creatDateEnd;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("PostName", getPostName())
                .append("Description", getDescription())
                .append("IsValid", getIsValid())
                .append("Icon", getIcon())
                .append("CreatDate", getCreatDate())
                .append("CreatDateBegin", getCreatDateBegin())
                .append("CreatDateEnd", getCreatDateEnd())
                .append("CreateNameId", getCreateNameId())
                .append("OrgId", getOrgId())
                .toString();
    }

}