package com.ctw.dao.roleuser;

import com.ctw.dao.IBaseDao;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.roleuser.RoleUser;
import com.ctw.domain.roleuser.RoleUserEntity;
import com.ctw.domain.roleuser.RoleUserQuery;

import java.util.List;

public interface IRoleUserDao extends IBaseDao<Integer, RoleUserEntity> {
    int batchDelete(String[] ids);

    int deleteById(String id);

    /**
     * 根据用户ID 角色ID 删除用户直属角色
     */
    int deleteByUIdRId(RoleUserEntity entiy);

    RoleUserEntity getById(String id);

    List<RoleUser> findList(RoleUserQuery query);

    PageResult<RoleUser> findPage(RoleUserQuery query);
    /**
     * 根据角色查找拥有本角色的用户列表集合
     *
     * @param query
     * @return
     */
    PageResult<RoleUser> findListByRoleId(RoleUserQuery query);
}