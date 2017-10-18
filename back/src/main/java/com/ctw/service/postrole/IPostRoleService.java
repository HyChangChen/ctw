package com.ctw.service.postrole;

import com.ctw.domain.postrole.PostRole;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.postrole.PostRoleQuery;
import com.ctw.service.IBaseService;

import java.util.List;

public interface IPostRoleService extends IBaseService<Integer, PostRole> {
    int batchDelete(String[] ids);

    /**
     * 删除岗位与角色的关联关系
     *
     * @param postRole
     * @return
     */
    int deleteByPostIdRId(PostRole postRole);

    List<PostRole> findList(PostRoleQuery query);

    /***
     * 根据角色ID 查找拥有本角色的岗位集合
     *
     * @param query
     * @return
     */
    List<PostRole> findListByRoleId(PostRoleQuery query);

    PageResult<PostRole> findPage(PostRoleQuery query);
}