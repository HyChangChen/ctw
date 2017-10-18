package com.ctw.dao.postrole;

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import com.ctw.domain.postrole.PostRole;
import com.ctw.domain.postrole.PostRoleEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.postrole.PostRoleQuery;

import java.util.List;

public interface IPostRoleDao extends IBaseDao<Integer, PostRoleEntity> {
        int batchDelete(String[] ids);
        /**
         * 删除岗位与角色的关联关系
         *
         * @param postRole
         * @return
         */
        int deleteByPostIdRId(PostRole postRole);
        List<PostRole> findList(PostRoleQuery query);
        PageResult<PostRole> findPage(PostRoleQuery query);
        /***
         * 根据角色ID 查找拥有本角色的岗位集合
         * @param query
         * @return
         */
        List<PostRole> findListByRoleId(PostRoleQuery query);
}