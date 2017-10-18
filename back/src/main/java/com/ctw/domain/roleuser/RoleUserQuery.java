package com.ctw.domain.roleuser;

import com.ctw.domain.common.PageQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RoleUserQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;


    /** id */
    private Integer id;

    /** 用户ID */
    private Integer uid;

    /** 角色ID */
    private Integer roleId;

    /** 时间 */
    private java.util.Date ts;
    
    /** 时间Begin */
    private java.util.Date tsBegin;

    /** 时间End */
    private java.util.Date tsEnd;

    public RoleUserQuery() {}

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return this.id;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public Integer getUid() {
        return this.uid;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("Id",getId())
            .append("Uid",getUid())
            .append("RoleId",getRoleId())
            .append("Ts",getTs())
            .append("TsBegin",getTsBegin())
            .append("TsEnd",getTsEnd())
            .toString();
    }

}