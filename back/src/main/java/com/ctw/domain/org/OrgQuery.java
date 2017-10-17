package com.ctw.domain.org;

import com.ctw.domain.common.PageQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrgQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;


    /** id */
    private Integer id;

    /** 组织名称 */
    private String orgName;

    /** 备注 */
    private Integer partentId;

    /** 是否根节点 */
    private Integer leaf;

    /** 是否生效 */
    private Integer isValid=1;

    /** ts */
    private java.util.Date ts;
    
    /** tsBegin */
    private java.util.Date tsBegin;

    /** tsEnd */
    private java.util.Date tsEnd;

    /** createNameId */
    private Integer createNameId;

    /** 样式 */
    private String icon;


    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public OrgQuery() {}

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return this.id;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getOrgName() {
        return this.orgName;
    }
    public void setPartentId(Integer partentId) {
        this.partentId = partentId;
    }
    public Integer getPartentId() {
        return this.partentId;
    }
    public void setLeaf(Integer leaf) {
        this.leaf = leaf;
    }
    public Integer getLeaf() {
        return this.leaf;
    }
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
    public Integer getIsValid() {
        return this.isValid;
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
    public void setCreateNameId(Integer createNameId) {
        this.createNameId = createNameId;
    }
    public Integer getCreateNameId() {
        return this.createNameId;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return this.icon;
    }

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("Id",getId())
            .append("OrgName",getOrgName())
            .append("PartentId",getPartentId())
            .append("Leaf",getLeaf())
            .append("IsValid",getIsValid())
            .append("Ts",getTs())
            .append("TsBegin",getTsBegin())
            .append("TsEnd",getTsEnd())
            .append("CreateNameId",getCreateNameId())
            .append("Icon",getIcon())
            .toString();
    }

}