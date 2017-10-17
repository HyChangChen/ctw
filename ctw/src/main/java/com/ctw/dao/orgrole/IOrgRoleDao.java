package com.ctw.dao.orgrole;

import com.ctw.dao.IBaseDao;
import com.ctw.domain.orgrole.OrgRole;
import com.ctw.domain.orgrole.OrgRoleEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.orgrole.OrgRoleQuery;

import java.util.List;

public interface IOrgRoleDao extends IBaseDao<Integer, OrgRoleEntity> {
    int batchDelete(String[] ids);

    List<OrgRole> findList(OrgRoleQuery query);

    PageResult<OrgRole> findPage(OrgRoleQuery query);
    /**
     * 根据角色ID 查找拥有本角色的组织列表
     * @param query
     * @return
     */
    List<OrgRole> findListByRoleId(OrgRoleQuery query);
    int deleteByOrgIdRId(OrgRole orgRole);

}