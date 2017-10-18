package com.ctw.dao.user.impl;


import com.ctw.dao.AbstractBaseDao;
import com.ctw.domain.common.PageResult;
import com.ctw.dao.user.IUserDao;

import org.springframework.stereotype.Repository;

import com.ctw.domain.user.User;
import com.ctw.domain.user.UserEntity;
import com.ctw.domain.user.UserQuery;


import java.util.List;
import java.util.Arrays;

@Repository("iUserDao")
public class UserDaoImpl extends AbstractBaseDao<Integer, UserEntity> implements IUserDao {
    private static final String NAMESPACE = "com.ctw.dao.user.IUserDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    private void rewriteSortColumns(UserQuery query) {

    }

    @Override
    public List<User> findList(UserQuery query) {
        rewriteSortColumns(query);
        return selectList("findList", query);
    }

    @Override
    public PageResult<User> findPage(UserQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findList", query);
    }

    /**
     * 下拉框 搜索
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<User> findAllUserList(UserQuery query) {
        return pageQuery("findAllUserList", query);
    }

    /***
     * 根据角色ID查找未被关联的用户
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<User> findUserNotLinkRolePage(UserQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findUserNotLinkRolePage", query);
    }

    /***
     * 根据组织ID 统计该组织下的用户数量
     *
     * @param orgId
     * @return
     */
    @Override
    public int getCountUserByOrg(Integer orgId) {
        Long totalCount = selectOne("getCountUserByOrg", orgId);
        return Integer.valueOf(totalCount.toString());
    }

    @Override
    public int create(UserEntity entity) {
        return super.create(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public int update(UserEntity entity) {
        return super.update(entity);
    }

    @Override
    public UserEntity getById(Integer id) {
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