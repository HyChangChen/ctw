package com.ctw.domain.user;

import com.ctw.domain.common.PageQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class UserQuery extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;


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
    private Integer genter;

    /** 注册时间 */
    private java.util.Date regTime;
    
    /** 注册时间Begin */
    private java.util.Date regTimeBegin;

    /** 注册时间End */
    private java.util.Date regTimeEnd;

    /** 所属组织 */
    private Integer orgId;

    /** 所属岗位 */
    private Integer postId;

    /** 状态[1.正常，0 锁定 2 过期 3 危险] */
    private Integer status;

    /** 腾讯QQ号 */
    private Integer qq;

    /** 是否生效 1生效 0 失效 */
    private Integer isValid;


    /***存储被关联的用户ID**/
    private List<Integer> linkUserIds;

    /**查询用户用到的角色**/
    private Integer roleId;

    /**用户搜索*/
    private String inputVal;

    public String getInputVal() {
        return inputVal;
    }

    public void setInputVal(String inputVal) {
        this.inputVal = inputVal;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getLinkUserIds() {
        return linkUserIds;
    }

    public void setLinkUserIds(List<Integer> linkUserIds) {
        this.linkUserIds = linkUserIds;
    }






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
    public void setRegTimeBegin(java.util.Date regTimeBegin) {
        this.regTimeBegin = regTimeBegin;
    }
    public java.util.Date getRegTimeBegin() {
        return this.regTimeBegin;
    }
    public void setRegTimeEnd(java.util.Date regTimeEnd) {
        this.regTimeEnd = regTimeEnd;
    }
    public java.util.Date getRegTimeEnd() {
        return this.regTimeEnd;
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
            .append("RegTimeBegin",getRegTimeBegin())
            .append("RegTimeEnd",getRegTimeEnd())
            .append("OrgId",getOrgId())
            .append("PostId",getPostId())
            .append("Status",getStatus())
            .append("Qq",getQq())
            .append("IsValid",getIsValid())
            .toString();
    }

}