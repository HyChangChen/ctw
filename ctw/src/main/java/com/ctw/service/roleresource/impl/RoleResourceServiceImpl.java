package com.ctw.service.roleresource.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.roleresource.IRoleResourceDao;
import com.ctw.domain.roleresource.RoleResourceEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.roleresource.RoleResource;
import com.ctw.domain.roleresource.RoleResourceQuery;
import com.ctw.domain.roleresource.RoleResourceVoConvert;
import com.ctw.service.roleresource.IRoleResourceService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RoleResourceServiceImpl implements IRoleResourceService {

    @Autowired
    private IRoleResourceDao iRoleResourceDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(RoleResource roleResource) {
        if (null == roleResource) {
            return 0;
        }
       /* if(StringUtils.isNotBlank(roleResource.getId().toString())){
            roleResource.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(roleResource);
        RoleResourceEntity roleResourceEntity = RoleResourceVoConvert.toEntity(roleResource);
        return iRoleResourceDao.create(roleResourceEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(RoleResource roleResource) {
        if (StringUtils.isEmpty(roleResource.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(roleResource);
        RoleResourceEntity roleResourceEntity = RoleResourceVoConvert.toEntity(roleResource);
        return iRoleResourceDao.update(roleResourceEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iRoleResourceDao.deleteById(id);
    }

    @Override
    public int batchDelete(String[] ids,Integer roleId) {
        return iRoleResourceDao.batchDelete(ids,roleId);
    }

    @Override
    public RoleResource getById(Integer id) {
        return RoleResourceVoConvert.toVo(iRoleResourceDao.getById(id));
    }

    @Override
    public List<RoleResource> findList(RoleResourceQuery query) {
        return iRoleResourceDao.findList(query);
    }

    @Override
    public PageResult<RoleResource> findPage(RoleResourceQuery query) {
        return iRoleResourceDao.findPage(query);
    }
}