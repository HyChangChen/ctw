package com.ctw.domain.user;

import javax.validation.constraints.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * t_sys_user
 */
public class UserEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	/** id db_column: id */
    private Integer id;

	/** 登录名 db_column: login_name */
    private String loginName;

	/** 密码 db_column: pass_word */
    private String passWord;

	/** 全称 db_column: full_name */
    private String fullName;

	/** 中文名 db_column: china_name */
    private String chinaName;

	/** 加密盐【登录名+密码 再进行加密】 db_column: salt */
    private String salt;

	/** 手机号码 db_column: mobile */
    private String mobile;

	/** 电话号码 db_column: tel */
    private String tel;

	/** 地址 db_column: address */
    private String address;

	/** 邮箱 db_column: e_mail */
    private String email;

	/** 年龄 db_column: age */
    private Integer age;

	/** 性别 db_column: genter */
    private Integer genter;

	/** 注册时间 db_column: reg_time */
    private java.util.Date regTime;

	/** 所属组织 db_column: org_id */
    private Integer orgId;

	/** 所属岗位 db_column: post_id */
    private Integer postId;

	/** 状态[1.正常，0 锁定 2 过期 3 危险] db_column: status */
    private Integer status;

	/** 腾讯QQ号 db_column: qq */
    private Integer qq;

	/** 是否生效 1生效 0 失效 db_column: is_valid */
    private Integer isValid=1;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setChinaName(String chinaName) {
        this.chinaName = chinaName;
    }

    public String getChinaName() {
        return this.chinaName;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return this.tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setGenter(Integer genter) {
        this.genter = genter;
    }

    public Integer getGenter() {
        return this.genter;
    }

    public void setRegTime(java.util.Date regTime) {
        this.regTime = regTime;
    }

    public java.util.Date getRegTime() {
        return this.regTime;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOrgId() {
        return this.orgId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getPostId() {
        return this.postId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    public Integer getQq() {
        return this.qq;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsValid() {
        return this.isValid;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("LoginName",getLoginName())
			.append("PassWord",getPassWord())
			.append("FullName",getFullName())
			.append("ChinaName",getChinaName())
			.append("Salt",getSalt())
			.append("Mobile",getMobile())
			.append("Tel",getTel())
			.append("Address",getAddress())
			.append("Email",getEmail())
			.append("Age",getAge())
			.append("Genter",getGenter())
			.append("RegTime",getRegTime())
			.append("OrgId",getOrgId())
			.append("PostId",getPostId())
			.append("Status",getStatus())
			.append("Qq",getQq())
			.append("IsValid",getIsValid())
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof UserEntity == false) return false;
		if(this == obj) return true;
		UserEntity other = (UserEntity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

