package com.ctw.dao.user;

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import com.ctw.domain.user.User;
import com.ctw.domain.user.UserEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.user.UserQuery;

import java.util.List;

public interface IUserDao extends IBaseDao<Integer, UserEntity> {
    int batchDelete(String[] ids);

    List<User> findList(UserQuery query);

    PageResult<User> findPage(UserQuery query);

    /**
     * 下拉框 搜索
     * @param query
     * @return
     */
    PageResult<User> findAllUserList(UserQuery query);

    /***
     * 根据角色ID查找未被关联的用户
     *
     * @param query
     * @return
     */
    PageResult<User> findUserNotLinkRolePage(UserQuery query);

    /***
     * 根据组织ID 统计该组织下的用户数量
     *
     * @param orgId
     * @return
     */
    int getCountUserByOrg(Integer orgId);

}