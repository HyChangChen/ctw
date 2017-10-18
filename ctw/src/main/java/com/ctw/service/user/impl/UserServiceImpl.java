package com.ctw.service.user.impl;


import com.ctw.domain.common.Select2Type;
import com.ctw.domain.org.OrgQuery;
import com.ctw.domain.orgrole.OrgRole;
import com.ctw.domain.orgrole.OrgRoleQuery;
import com.ctw.domain.postrole.PostRole;
import com.ctw.domain.postrole.PostRoleQuery;
import com.ctw.domain.resource.ResourceQuery;
import com.ctw.domain.resource.ResourceVo;
import com.ctw.domain.role.Role;
import com.ctw.domain.roleresource.RoleResource;
import com.ctw.domain.roleresource.RoleResourceQuery;
import com.ctw.domain.roleuser.RoleUser;
import com.ctw.domain.roleuser.RoleUserQuery;
import com.ctw.exception.BusinessException;
import com.ctw.service.orgrole.IOrgRoleService;
import com.ctw.service.postrole.IPostRoleService;
import com.ctw.service.resource.IResourceService;
import com.ctw.service.role.IRoleService;
import com.ctw.service.roleresource.IRoleResourceService;
import com.ctw.service.roleuser.IRoleUserService;
import com.ctw.utils.PasswordHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.user.IUserDao;
import com.ctw.domain.user.UserEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.user.User;
import com.ctw.domain.user.UserQuery;
import com.ctw.domain.user.UserVoConvert;
import com.ctw.service.user.IUserService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    @Qualifier(value = "iUserDao")
    protected IUserDao iUserDao;
    @Autowired
    protected IPostRoleService iPostRoleService;
    @Autowired
    protected IOrgRoleService iOrgRoleService;
    @Autowired
    protected IRoleUserService iRoleUserService;
    @Autowired
    protected IRoleResourceService iRoleResourceService;
    @Autowired
    protected IResourceService iResourceService;
    @Autowired
    protected IRoleService iRoleService;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(User user) {
        if (null == user) {
            return 0;
        }
      /*  if(StringUtils.isNumeric(user.getId().toString())){
            user.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);
        UserEntity userEntity = UserVoConvert.toEntity(user);
        return iUserDao.create(userEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(User user) {
        if (StringUtils.isEmpty(user.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(user);
        if (null != user.getPassWord() && !"".equals(user.getPassWord())) {
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
        }
        UserEntity userEntity = UserVoConvert.toEntity(user);
        return iUserDao.update(userEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iUserDao.deleteById(id);
    }

    @Override
    public int batchDelete(String[] ids) {
        return iUserDao.batchDelete(ids);
    }

    @Override
    public User getById(Integer id) {
        return UserVoConvert.toVo(iUserDao.getById(id));
    }

    @Override
    public List<User> findList(UserQuery query) {
        return iUserDao.findList(query);
    }

    @Override
    public PageResult<User> findPage(UserQuery query) {
        return iUserDao.findPage(query);
    }

    /**
     * 根据角色ID 查找没有被本角色关联的用户
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<User> findUserNotLinkRolePage(UserQuery query) {
        //查询所有的跟本角色关联的用户ID
        RoleUserQuery roleUserQuery = new RoleUserQuery();
        roleUserQuery.setRoleId(query.getRoleId());
        List<RoleUser> list = iRoleUserService.findList(roleUserQuery);
        List<Integer> uIds = new ArrayList<Integer>();
        if (list.size() > 0) {
            for (RoleUser r : list) {
                uIds.add(r.getUid());
            }
            query.setLinkUserIds(uIds);
        }
        return iUserDao.findUserNotLinkRolePage(query);
    }


    /**
     * 根据用户名称查找用户的角色组，权限组
     *
     * @param loginName
     * @return
     */
    @Override
    public User findUserRolds(String loginName) {
        UserQuery query = new UserQuery();
        User user = new User();
        query.setLoginName(loginName);
        List<User> userList = iUserDao.findList(query);
        if (userList.size() == 1) {
            user = userList.get(0);
            //根据拥有的岗位ID 查找岗位所拥有的角色id列表
            PostRoleQuery postRoleQuery = new PostRoleQuery();
            postRoleQuery.setPsotId(user.getPostId());
            List<PostRole> postRoleList = iPostRoleService.findList(postRoleQuery);
            //根据拥有的组织ID 查找所拥有的角色id列表
            OrgRoleQuery orgRoleQuery = new OrgRoleQuery();
            orgRoleQuery.setOrgId(user.getOrgId());
            List<OrgRole> orgRolesList = iOrgRoleService.findList(orgRoleQuery);
            //用户的ID 查找用户直接拥有的角色ID组
            RoleUserQuery roleUserQuery = new RoleUserQuery();
            roleUserQuery.setUid(user.getId());
            List<RoleUser> userRoleList = iRoleUserService.findList(roleUserQuery);

            List<Integer> tempListroldes = new ArrayList<Integer>();
            if (postRoleList != null && postRoleList.size() > 0) {
                for (PostRole pr : postRoleList) {
                    tempListroldes.add(pr.getRoleId());
                }
            }
            if (orgRolesList != null && orgRolesList.size() > 0) {
                for (OrgRole or : orgRolesList) {
                    tempListroldes.add(or.getRoleId());
                }
            }
            if (userRoleList != null && userRoleList.size() > 0) {
                for (RoleUser ur : userRoleList) {
                    tempListroldes.add(ur.getRoleId());
                }
            }
            List<Integer> roleIds = new ArrayList<Integer>();
            if (tempListroldes.size() > 0) {
                for (Integer i : tempListroldes) {
                    if (!roleIds.contains(i)) {
                        roleIds.add(i);
                    }
                }
            }
            user.setRoleIds(roleIds);
            Set<String> permission = new HashSet<String>();
            if (null != roleIds && roleIds.size() > 0) {
                RoleResourceQuery roleResourceQuery = new RoleResourceQuery();
                roleResourceQuery.setRoldIds(roleIds);
                List<RoleResource> roleResourceList = iRoleResourceService.findList(roleResourceQuery);
                List<Integer> resourceIdsTemp = new ArrayList<Integer>();
                if (roleResourceList != null && roleResourceList.size() > 0) {
                    for (RoleResource resource : roleResourceList) {
                        resourceIdsTemp.add(resource.getResourceId());
                    }
                }
                List<Integer> resourceIds = new ArrayList<Integer>();
                if (resourceIdsTemp.size() > 0) {
                    for (Integer i : resourceIdsTemp) {
                        if (!resourceIds.contains(i)) {
                            resourceIds.add(i);
                        }
                    }
                }
                Set<String> roles = new HashSet<String>();
                for (Integer integer : roleIds) {
                    Role role = iRoleService.getById(integer);
                    roles.add(role.getRoleName());
                }
                user.setRoleNames(roles);

                user.setResourceIds(resourceIds);
                if (resourceIds != null && resourceIds.size() > 0) {
                    ResourceQuery resourceQuery = new ResourceQuery();
                    resourceQuery.setResourceIds(resourceIds);
                    List<ResourceVo> resourceList = iResourceService.findList(resourceQuery);
                    if (null != resourceList && resourceList.size() > 0) {
                        for (ResourceVo resourceVo : resourceList) {
                            if (null != resourceVo.getPermission() && !("").equals(resourceVo.getPermission()))
                                permission.add(resourceVo.getPermission());
                        }
                    }
                }
            }
            user.setPermission(permission);

        } else {
            throw new BusinessException("用户未找到: " + user.getFullName());
        }
        return user;
    }

    @Override
    public List<User> findUser(String loginName) {
        UserQuery query = new UserQuery();
        query.setLoginName(loginName);
        List<User> users = iUserDao.findList(query);
        return users;
    }

    /**
     * 用户修改密码
     *
     * @param user
     * @return
     */

    public int updatePassWord(User user) {
        UserEntity dbU = iUserDao.getById(user.getId());
        int num = 0;
        if (null == dbU) {
            return 0;
        }
        PasswordHelper passwordHelper = new PasswordHelper();
        user.setSalt(dbU.getSalt());
        user.setLoginName(dbU.getLoginName());
        user.setPassWord(user.getOldPwd());
        passwordHelper.encryptPassword(user);
        if (user.getPassWord().equals(dbU.getPassWord())) {
            user.setSalt(null);
            user.setPassWord(user.getNewPwd());
            passwordHelper.encryptPassword(user);
            UserEntity userEntity = UserVoConvert.toEntity(user);
            num = iUserDao.update(userEntity);
        }
        return num;
    }

    /***
     * 根据组织ID 统计该组织下的用户数量
     *
     * @param orgId
     * @return
     */
    public int getCountUserByOrg(Integer orgId) {
        return iUserDao.getCountUserByOrg(orgId);
    }

    /**
     * 下拉框 搜索
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<Select2Type> findAllUserList(UserQuery query) {
        PageResult<User> userList=iUserDao.findAllUserList(query);
        List<Select2Type> select2TypeList=new ArrayList<Select2Type>();
        for(User u:userList.getRows()){
            Select2Type s2=new Select2Type();
            s2.setId(u.getId().longValue());
            s2.setText(u.getLoginName() +" "+u.getChinaName()+" "+u.getFullName());
            select2TypeList.add(s2);
        }
        PageResult<Select2Type>  pageResult =new PageResult<Select2Type>();
        pageResult.setTotal(userList.getTotal());
        pageResult.setRows(select2TypeList);
        return pageResult;
    }
}