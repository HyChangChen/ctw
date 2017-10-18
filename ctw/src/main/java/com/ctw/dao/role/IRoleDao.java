package com.ctw.dao.role;

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import com.ctw.domain.role.Role;
import com.ctw.domain.role.RoleEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.role.RoleQuery;

import java.util.List;

public interface IRoleDao extends IBaseDao<Integer, RoleEntity> {
        int batchDelete(String[] ids);
        List<Role> findList(RoleQuery query);
        PageResult<Role> findPage(RoleQuery query);
}