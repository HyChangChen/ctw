package com.ctw.dao.roleresource.impl;


import com.ctw.dao.AbstractBaseDao;
import com.ctw.domain.common.PageResult;
import com.ctw.dao.roleresource.IRoleResourceDao;

import org.springframework.stereotype.Repository;

import com.ctw.domain.roleresource.RoleResource;
import com.ctw.domain.roleresource.RoleResourceEntity;
import com.ctw.domain.roleresource.RoleResourceQuery;


import java.util.List;
import java.util.Arrays;

@Repository("iRoleResourceDao")
public class RoleResourceDaoImpl extends AbstractBaseDao<Integer, RoleResourceEntity> implements IRoleResourceDao {
    private static final String NAMESPACE = "com.ctw.dao.roleresource.IRoleResourceDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    private void rewriteSortColumns(RoleResourceQuery query) {

    }

    @Override
    public List<RoleResource> findList(RoleResourceQuery query) {
        rewriteSortColumns(query);
        return selectList("findList", query);
    }

    @Override
    public PageResult<RoleResource> findPage(RoleResourceQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findList", query);
    }

    @Override
    public int create(RoleResourceEntity entity) {
        return super.create(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public int update(RoleResourceEntity entity) {
        return super.update(entity);
    }

    @Override
    public RoleResourceEntity getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public int batchDelete(String[] ids, Integer roleId) {
        int i = 0;
        List<String> idsTemp = Arrays.asList(ids);
        if (idsTemp.size() > 0) {
            for (String id : idsTemp) {
                RoleResourceQuery roleResourceQuery = new RoleResourceQuery();
                roleResourceQuery.setResourceId(Integer.parseInt(id));
                roleResourceQuery.setRoleId(roleId);
                    i += executeDelete("deleteByRoleIdResourceid", roleResourceQuery);
            }
        }
        return i;
    }

}