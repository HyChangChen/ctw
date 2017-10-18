package com.ctw.service.roleuser;

import com.ctw.domain.common.PageResult;
import com.ctw.domain.roleresource.RoleResource;
import com.ctw.domain.roleuser.RoleUser;
import com.ctw.domain.roleuser.RoleUserQuery;
import com.ctw.service.IBaseService;

import java.util.List;

public interface IRoleUserService extends IBaseService<Integer, RoleUser> {
    int batchDelete(String[] ids);

    /**
     * 根据用户ID 角色ID 删除用户直属角色
     */
    int deleteByUIdRId(RoleUser roleUser);

    List<RoleUser> findList(RoleUserQuery query);

    /**
     * 根据角色查找拥有本角色的用户列表集合
     *
     * @param query
     * @return
     */
    PageResult<RoleUser> findListByRoleId(RoleUserQuery query);

    PageResult<RoleUser> findPage(RoleUserQuery query);
}