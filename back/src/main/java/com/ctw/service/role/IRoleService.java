package com.ctw.service.role;

import com.ctw.domain.role.Role;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.role.RoleQuery;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface IRoleService extends IBaseService<Integer ,Role> {
    int batchDelete(String[] ids);
    List<Role> findList(RoleQuery query);
    PageResult<Role> findPage(RoleQuery query);
    /**
     * 查找拥有的的角色列表
     */
    PageResult<Role> findHaveListPage(RoleQuery query);
    /**
     *统计角色ID 是否关联了用户 组织 岗位
     */
    int getCountByRole(String[] ids);
}