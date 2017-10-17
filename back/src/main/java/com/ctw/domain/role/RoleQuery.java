package com.ctw.domain.role;

import com.ctw.domain.common.PageQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class RoleQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;


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
    
    /** 创建时间Begin */
    private java.util.Date createDateBegin;

    /** 创建时间End */
    private java.util.Date createDateEnd;

    /** 创建人 */
    private Integer createNameId;

    /**用户ID*/
    private Integer userId;
    /**组织ID*/
    private Integer orgId;
    /**岗位ID*/
    private Integer postId;

    /**角色Id j结合*/
    private List<Integer> roleIds;

    /**为用户查询的拥有角色和可拥有的角色而设定的标识 */
    private  String findType;

    public String getFindType() {
        return findType;
    }

    public void setFindType(String findType) {
        this.findType = findType;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public RoleQuery() {}

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
    public void setCreateDateBegin(java.util.Date createDateBegin) {
        this.createDateBegin = createDateBegin;
    }
    public java.util.Date getCreateDateBegin() {
        return this.createDateBegin;
    }
    public void setCreateDateEnd(java.util.Date createDateEnd) {
        this.createDateEnd = createDateEnd;
    }
    public java.util.Date getCreateDateEnd() {
        return this.createDateEnd;
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
            .append("CreateDateBegin",getCreateDateBegin())
            .append("CreateDateEnd",getCreateDateEnd())
            .append("CreateNameId",getCreateNameId())
            .toString();
    }

}