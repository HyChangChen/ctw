package com.ctw.domain.roleresource;

import com.ctw.domain.common.PageQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class RoleResourceQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;


    /**
     * id
     */
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 资源ID
     */
    private Integer resourceId;

    /**
     * ts
     */
    private java.util.Date ts;

    /**
     * tsBegin
     */
    private java.util.Date tsBegin;

    /**
     * tsEnd
     */
    private java.util.Date tsEnd;


    /**
     * 角色ID集合
     */
    private List<Integer> roldIds;

    public List<Integer> getRoldIds() {
        return roldIds;
    }

    public void setRoldIds(List<Integer> roldIds) {
        this.roldIds = roldIds;
    }

    public RoleResourceQuery() {
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

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("RoleId", getRoleId())
                .append("ResourceId", getResourceId())
                .append("Ts", getTs())
                .append("TsBegin", getTsBegin())
                .append("TsEnd", getTsEnd())
                .toString();
    }

}