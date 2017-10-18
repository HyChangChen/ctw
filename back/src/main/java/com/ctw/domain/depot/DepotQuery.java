package com.ctw.domain.depot;

import com.ctw.domain.common.PageQuery;

public class DepotQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;

    private Long id;//id
    private String depotNo;//仓库编号
    private String depotName;//仓库名称
    private String depotManager;//创库管理者
    private String address;//仓库地址
    private Integer isValid;//状态[1,有效，0.无效]
    private Integer isDefault;//是否默认仓库

    /**
     * 自定义属性
     */

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepotNo() {
        return this.depotNo;
    }

    public void setDepotNo(String depotNo) {
        this.depotNo = depotNo;
    }

    public String getDepotName() {
        return this.depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public String getDepotManager() {
        return this.depotManager;
    }

    public void setDepotManager(String depotManager) {
        this.depotManager = depotManager;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsValid() {
        return this.isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

}