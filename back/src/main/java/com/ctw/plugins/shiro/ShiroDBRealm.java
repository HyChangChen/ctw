package com.ctw.plugins.shiro;

import com.ctw.domain.role.Role;
import com.ctw.domain.role.RoleQuery;
import com.ctw.domain.user.User;
import com.ctw.service.role.IRoleService;
import com.ctw.service.user.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/27.22:57
 * @Vistion：1.0
 * @Remark： shiro 框架中自定义的realm
 */
public class ShiroDBRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(ShiroDBRealm.class);
    /*private static final int INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;
    private static final String ALGORITHM = "SHA-1";*/

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    // 是否启用超级管理员
    protected boolean activeRoot = true;
    // 是否使用验证码
    protected boolean useCaptcha = false;

    /**
     * 给ShiroDbRealm提供编码信息，用于密码密码比对
     * 描述
     */
  /*  public ShiroRealm() {
        super();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
        matcher.setHashIterations(INTERATIONS);

        setCredentialsMatcher(matcher);
    }*/

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Collection<?> collection = principals.fromRealm(getName());
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        //查找用户信息
        List<User> users = iUserService.findUser((String) principals.getPrimaryPrincipal());
        User shiroUser=new User();
        if (users.size() > 0) {
              shiroUser=users.get(0);
        }
       // User shiroUser = (User) collection.iterator().next();

        return newAuthorizationInfo(shiroUser);

    }

    private SimpleAuthorizationInfo newAuthorizationInfo(User shiroUser) {
        Collection<String> hasPermissions = null;
        Collection<String> hasRoles = null;
        User u = new User();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 是否启用超级管理员 && id==1为超级管理员，构造所有权限
        if (activeRoot && shiroUser.getId() == 1) {
            hasRoles = new HashSet<String>();
            RoleQuery roleQuery = new RoleQuery();
            roleQuery.setIsValid(null);
            List<Role> roles = iRoleService.findList(roleQuery);

            for (Role role : roles) {
                hasRoles.add(role.getRoleName());
            }

            hasPermissions = new HashSet<String>();
            hasPermissions.add("*");

            if (log.isInfoEnabled()) {
                log.info("使用了" + shiroUser.getLoginName() + "的超级管理员权限:" + "。At " + new Date());
                log.info(shiroUser.getLoginName() + "拥有的角色:" + hasRoles);
                log.info(shiroUser.getLoginName() + "拥有的权限:" + hasPermissions);
            }
            info.addRoles(hasRoles);
            info.addStringPermissions(hasPermissions);
        } else {
            //查找用户权限
            u = iUserService.findUserRolds(shiroUser.getLoginName());
            info.setRoles(u.getRoleNames());
            info.setStringPermissions(u.getPermission());
        }
        return info;
    }

    /**
     * shiro 用户匹配
     * 认证回调函数, 登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        List<User> users = iUserService.findUser((String) token.getPrincipal());
        User user = null;
        if (users.size() > 0) {
            user = users.get(0);
        }
        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

       /* if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }*/

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getLoginName(), //用户名
                user.getPassWord(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }



}
