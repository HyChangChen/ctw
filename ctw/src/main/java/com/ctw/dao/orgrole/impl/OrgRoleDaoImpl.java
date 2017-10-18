package com.ctw.dao.orgrole.impl;


import com.ctw.dao.AbstractBaseDao;
import com.ctw.domain.common.PageResult;
import com.ctw.dao.orgrole.IOrgRoleDao;

import org.springframework.stereotype.Repository;

import com.ctw.domain.orgrole.OrgRole;
import com.ctw.domain.orgrole.OrgRoleEntity;
import com.ctw.domain.orgrole.OrgRoleQuery;


import java.util.List;
import java.util.Arrays;

@Repository("iOrgRoleDao")
public class OrgRoleDaoImpl extends AbstractBaseDao<Integer, OrgRoleEntity> implements IOrgRoleDao {
    private static final String NAMESPACE = "com.ctw.dao.orgrole.IOrgRoleDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    private void rewriteSortColumns(OrgRoleQuery query) {

    }

    @Override
    public List<OrgRole> findList(OrgRoleQuery query) {
        rewriteSortColumns(query);
        return selectList("findList", query);
    }

    @Override
    public PageResult<OrgRole> findPage(OrgRoleQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findList", query);
    }

    /**
     * 根据角色ID 查找拥有本角色的组织列表
     *
     * @param query
     * @return
     */
    @Override
    public List<OrgRole> findListByRoleId(OrgRoleQuery query) {
        rewriteSortColumns(query);
        return selectList("findListByRoleId", query);
    }

    @Override
    public int deleteByOrgIdRId(OrgRole entity) {
        return executeDelete("deleteByOrgIdRId", entity);
    }

    @Override
    public int create(OrgRoleEntity entity) {
        return super.create(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public int update(OrgRoleEntity entity) {
        return super.update(entity);
    }

    @Override
    public OrgRoleEntity getById(Integer id) {
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