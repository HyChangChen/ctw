package com.ctw.service.orgrole;

import com.ctw.domain.orgrole.OrgRole;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.orgrole.OrgRoleQuery;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface IOrgRoleService extends IBaseService<Integer ,OrgRole> {
    int batchDelete(String[] ids);
    List<OrgRole> findList(OrgRoleQuery query);

    /**
     * 根据角色ID 查找拥有本角色的组织列表
     * @param query
     * @return
     */
    List<OrgRole> findListByRoleId(OrgRoleQuery query);
    PageResult<OrgRole> findPage(OrgRoleQuery query);
    int deleteByOrgIdRId(OrgRole orgRole);
}