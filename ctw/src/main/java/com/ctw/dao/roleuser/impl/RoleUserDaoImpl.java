package com.ctw.dao.roleuser.impl;

import com.ctw.dao.AbstractBaseDao;
import com.ctw.dao.roleuser.IRoleUserDao;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.roleuser.RoleUser;
import com.ctw.domain.roleuser.RoleUserEntity;
import com.ctw.domain.roleuser.RoleUserQuery;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("iRoleUserDao")
public class RoleUserDaoImpl extends AbstractBaseDao<Integer, RoleUserEntity> implements IRoleUserDao {
    private static final String NAMESPACE = "com.ctw.dao.roleuser.IRoleUserDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
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

    @Override
    public int deleteById(String id) {
        return deleteById(id);
    }

    /**
     * 根据用户ID 角色ID 删除用户直属角色
     *
     * @param entity
     *
     */
    @Override
    public int deleteByUIdRId(RoleUserEntity entity) {
        return executeDelete("deleteByUIdRId",entity);
    }

    @Override
    public RoleUserEntity getById(String id) {
        return super.getById(Integer.parseInt(id));
    }

    @Override
    public PageResult<RoleUser> findPage(RoleUserQuery query) {
        return pageQuery("findList", query);
    }

    /**
     * 根据角色查找拥有本角色的用户列表集合
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<RoleUser> findListByRoleId(RoleUserQuery query) {
        return pageQuery("findListByRoleId", query);
    }

    @Override
    public List<RoleUser> findList(RoleUserQuery query) {
        return selectList("findList", query);
    }


    @Override
    public int update(RoleUserEntity entity) {

        return update(entity);
    }

    @Override
    public int create(RoleUserEntity entity) {
        return super.create(entity);
    }

}