package com.ctw.dao.resource;

import com.ctw.dao.IBaseDao;
import com.ctw.domain.resource.ResourceVo;
import com.ctw.domain.resource.ResourceEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.resource.ResourceQuery;

import java.util.List;

public interface IResourceDao extends IBaseDao<Integer, ResourceEntity> {
    int batchDelete(String[] ids);

    List<ResourceVo> findList(ResourceQuery query);

    PageResult<ResourceVo> findPage(ResourceQuery query);

    /**
     * 查询所有的
     *
     * @param query
     * @return
     */
    PageResult<ResourceVo> findAllPage(ResourceQuery query);
}