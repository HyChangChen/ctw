package com.ctw.service.roleresource;

import com.ctw.domain.roleresource.RoleResource;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.roleresource.RoleResourceQuery;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface IRoleResourceService extends IBaseService<Integer ,RoleResource> {
    int batchDelete(String[] ids, Integer roleId);
    List<RoleResource> findList(RoleResourceQuery query);
    PageResult<RoleResource> findPage(RoleResourceQuery query);
}