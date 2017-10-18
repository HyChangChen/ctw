package com.ctw.dao.resource.impl;


import com.ctw.dao.AbstractBaseDao;
import com.ctw.domain.common.PageResult;
import com.ctw.dao.resource.IResourceDao;

import com.ctw.domain.resource.ResourceQuery;
import org.springframework.stereotype.Repository;

import com.ctw.domain.resource.ResourceVo;
import com.ctw.domain.resource.ResourceEntity;


import java.util.List;
import java.util.Arrays;

@Repository("iResourceVoDao")
public class ResourceVoDaoImpl extends AbstractBaseDao<Integer, ResourceEntity> implements IResourceDao {
    private static final String NAMESPACE = "com.ctw.dao.resource.IResourceVoDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    private void rewriteSortColumns(ResourceQuery query) {

    }

    @Override
    public List<ResourceVo> findList(ResourceQuery query) {
        rewriteSortColumns(query);
        return selectList("findList", query);
    }

    @Override
    public PageResult<ResourceVo> findPage(ResourceQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findList", query);
    }

    /**
     * 查询所有的
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<ResourceVo> findAllPage(ResourceQuery query) {
        return pageQuery("findAllList", query);
    }

    @Override
    public int create(ResourceEntity entity) {
        return super.create(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public int update(ResourceEntity entity) {
        //partentIds重组
        ResourceEntity resourceEntity = super.getById(entity.getParentId());
        ResourceEntity resourceEntity2 = super.getById(entity.getParentId());
        resourceEntity2.setLeaf("1");
        super.update(resourceEntity2);
        entity.setPartentsIds(resourceEntity.getPartentsIds() ==null?entity.getId()+"/":resourceEntity.getPartentsIds()+ entity.getId()+"/");
        return super.update(entity);
    }

    @Override
    public ResourceEntity getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public int batchDelete(String[] ids) {
        int i = 0;
        List<String> idsTemp = Arrays.asList(ids);
        if (idsTemp.size() > 0) {
            for (String id : idsTemp) {
                i += super.deleteById(Integer.parseInt(id));
            }
        }
        return i;
    }

}