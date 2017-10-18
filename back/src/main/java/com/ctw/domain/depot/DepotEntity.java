package com.ctw.domain.depot;

/**
 * t_bus_depot
 */
public class DepotEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	private Long id;//id db_column: id
	private String depotNo;//仓库编号 db_column: depot_No
	private String depotName;//仓库名称 db_column: depot_name
	private String depotManager;//创库管理者 db_column: depot_manager
	private String address;//仓库地址 db_column: address
	private Integer isValid;//状态[1,有效，0.无效] db_column: is_valid
	private Integer isDefault;//是否默认仓库 db_column: is_default

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setDepotNo(String depotNo) {
		this.depotNo = depotNo;
	}

	public String getDepotNo() {
		return this.depotNo;
	}

	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}

	public String getDepotName() {
		return this.depotName;
	}

	public void setDepotManager(String depotManager) {
		this.depotManager = depotManager;
	}

	public String getDepotManager() {
		return this.depotManager;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getIsDefault() {
		return this.isDefault;
	}

}
