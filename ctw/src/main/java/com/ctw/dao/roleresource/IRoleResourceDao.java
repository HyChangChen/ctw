package com.ctw.dao.roleresource;

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import com.ctw.domain.roleresource.RoleResource;
import com.ctw.domain.roleresource.RoleResourceEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.roleresource.RoleResourceQuery;

import java.util.List;

public interface IRoleResourceDao extends IBaseDao<Integer, RoleResourceEntity> {
        int batchDelete(String[] ids, Integer roleId);
        List<RoleResource> findList(RoleResourceQuery query);
        PageResult<RoleResource> findPage(RoleResourceQuery query);
}