package com.ctw.domain.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;


	/** id */
    private Integer id;

	/** 登录名 */
    private String loginName;

	/** 密码 */
    private String passWord;

	/** 全称 */
    private String fullName;

	/** 中文名 */
    private String chinaName;

	/** 加密盐【登录名+密码 再进行加密】 */
    private String salt;

	/** 手机号码 */
    private String mobile;

	/** 电话号码 */
    private String tel;

	/** 地址 */
    private String address;

	/** 邮箱 */
    private String email;

	/** 年龄 */
    private Integer age;

	/** 性别 */
    private Integer genter=1;

	/** 注册时间 */
    private java.util.Date regTime =new Date();
	/** 所属组织 */
    private Integer orgId;

	/** 所属岗位 */
    private Integer postId;

	/** 状态[1.正常，0 锁定 2 过期 3 危险] */
    private Integer status;

	/** 腾讯QQ号 */
    private Integer qq;

	/** 是否生效 1生效 0 失效 */
    private Integer isValid=1;
    /****
     * 自添属性
     */
    private String oldPwd;
    private String newPwd;

    private String ipAddress;
    private String clientMac;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getClientMac() {
        return clientMac;
    }

    public void setClientMac(String clientMac) {
        this.clientMac = clientMac;
    }



    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    private String postName;
    private String orgName;
    private Set<String> permission;
    private Set<String> roleNames;
    private StringBuffer htmlMenu;



    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }



    public StringBuffer getHtmlMenu() {
        return htmlMenu;
    }

    public void setHtmlMenu(StringBuffer htmlMenu) {
        this.htmlMenu = htmlMenu;
    }

    public Set<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(Set<String> roleNames) {
        this.roleNames = roleNames;
    }

    private List<Integer> resourceIds;

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Set<String> getPermission() {
        return permission;
    }

    public void setPermission(Set<String> permission) {
        if (null == permission) {
            permission = new HashSet<String>();
        }
        this.permission = permission;
    }
    /**
     * 得到用户盐
     * @return
     */
    public  String getCredentialsSalt(){
        return loginName+salt;
    }







    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    private List<Integer> roleIds; //拥有的角色列表

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
		if(obj instanceof User == false) return false;
		if(this == obj) return true;
		User other = (User)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

