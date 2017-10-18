package com.ctw.dao.postrole.impl;


import com.ctw.dao.AbstractBaseDao;
import com.ctw.domain.common.PageResult;
import com.ctw.dao.postrole.IPostRoleDao;

import org.springframework.stereotype.Repository;

import com.ctw.domain.postrole.PostRole;
import com.ctw.domain.postrole.PostRoleEntity;
import com.ctw.domain.postrole.PostRoleQuery;


import java.util.List;
import java.util.Arrays;

@Repository("iPostRoleDao")
public class PostRoleDaoImpl extends AbstractBaseDao<Integer, PostRoleEntity> implements IPostRoleDao {
    private static final String NAMESPACE = "com.ctw.dao.postrole.IPostRoleDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    private void rewriteSortColumns(PostRoleQuery query) {

    }

    @Override
    public List<PostRole> findList(PostRoleQuery query) {
        rewriteSortColumns(query);
        return selectList("findList", query);
    }

    @Override
    public PageResult<PostRole> findPage(PostRoleQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findList", query);
    }

    /***
     * 根据角色ID 查找拥有本角色的岗位集合
     *
     * @param query
     * @return
     */
    @Override
    public List<PostRole> findListByRoleId(PostRoleQuery query) {
        rewriteSortColumns(query);
        return selectList("findListByRoleId", query);
    }

    @Override
    public int create(PostRoleEntity entity) {
        return super.create(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public int update(PostRoleEntity entity) {
        return super.update(entity);
    }

    @Override
    public PostRoleEntity getById(Integer id) {
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

    /**
     * 删除岗位与角色的关联关系
     *
     * @param postRole
     * @return
     */
    @Override
    public int deleteByPostIdRId(PostRole postRole) {
        return executeDelete("deleteByPostIdRId", postRole);
    }

}