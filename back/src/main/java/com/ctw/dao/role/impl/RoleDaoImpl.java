package com.ctw.dao.role.impl;


import com.ctw.dao.AbstractBaseDao;
import com.ctw.domain.common.PageResult;
import com.ctw.dao.role.IRoleDao;

import org.springframework.stereotype.Repository;

import com.ctw.domain.role.Role;
import com.ctw.domain.role.RoleEntity;
import com.ctw.domain.role.RoleQuery;


import java.util.List;
import java.util.Arrays;

@Repository("iRoleDao")
public class RoleDaoImpl extends AbstractBaseDao<Integer, RoleEntity> implements IRoleDao {
    private static final String NAMESPACE = "com.ctw.dao.role.IRoleDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    private void rewriteSortColumns(RoleQuery query) {

    }

    @Override
    public List<Role> findList(RoleQuery query) {
        rewriteSortColumns(query);
        return selectList("findList", query);
    }

    @Override
    public PageResult<Role> findPage(RoleQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findList", query);
    }

    @Override
    public int create(RoleEntity entity) {
        return super.create(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public int update(RoleEntity entity) {
        return super.update(entity);
    }

    @Override
    public RoleEntity getById(Integer id) {
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