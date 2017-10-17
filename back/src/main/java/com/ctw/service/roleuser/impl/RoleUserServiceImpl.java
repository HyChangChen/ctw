package com.ctw.service.roleuser.impl;


import com.ctw.dao.roleuser.IRoleUserDao;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.roleuser.RoleUser;
import com.ctw.domain.roleuser.RoleUserEntity;
import com.ctw.domain.roleuser.RoleUserQuery;
import com.ctw.domain.roleuser.RoleUserVoConvert;
import com.ctw.service.roleuser.IRoleUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("iRoleUserService")
public class RoleUserServiceImpl implements IRoleUserService {

    @Autowired
    @Qualifier(value = "iRoleUserDao")
    private IRoleUserDao iRoleUserDao;


    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(RoleUser roleUser) {
        if (null == roleUser) {
            return 0;
        }
        RoleUserEntity roleUserEntity = RoleUserVoConvert.toEntity(roleUser);
        return iRoleUserDao.create(roleUserEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(RoleUser roleUser) {
        if (StringUtils.isEmpty(roleUser.getId().toString())) {
            return 0;
        }
        RoleUserEntity roleUserEntity = RoleUserVoConvert.toEntity(roleUser);
        return iRoleUserDao.update(roleUserEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iRoleUserDao.deleteById(id);
    }

    @Override
    public int batchDelete(String[] ids) {
        return iRoleUserDao.batchDelete(ids);
    }

    /**
     * 根据用户ID 角色ID 删除用户直属角色
     */
    @Override
    public int deleteByUIdRId(RoleUser roleUser) {
        return iRoleUserDao.deleteByUIdRId(RoleUserVoConvert.toEntity(roleUser));
    }


    @Override
    public RoleUser getById(Integer id) {
        return RoleUserVoConvert.toVo(iRoleUserDao.getById(id));
    }

    @Override
    public List<RoleUser> findList(RoleUserQuery query) {
        return iRoleUserDao.findList(query);
    }

    /**
     * 根据角色查找拥有本角色的用户列表集合
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<RoleUser> findListByRoleId(RoleUserQuery query) {
        return iRoleUserDao.findListByRoleId(query);
    }

    @Override
    public PageResult<RoleUser> findPage(RoleUserQuery query) {
        return iRoleUserDao.findPage(query);
    }
}