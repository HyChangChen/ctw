package com.ctw.service.user;

import com.ctw.domain.common.Select2Type;
import com.ctw.domain.user.User;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.user.UserQuery;
import com.ctw.service.IBaseService;

import java.util.List;

public interface IUserService extends IBaseService<Integer, User> {
    int batchDelete(String[] ids);

    List<User> findList(UserQuery query);

    PageResult<User> findPage(UserQuery query);

    /**
     * 根据角色ID 查找没有被本角色关联的用户
     *
     * @param query
     * @return
     */
    PageResult<User> findUserNotLinkRolePage(UserQuery query);

    /**
     * 根据用户登录名称查找用户的角色组，权限组
     *
     * @param loginName
     * @return
     */
    User findUserRolds(String loginName);

    /**
     * 根据用户输入的用户名查询用户的信息
     */
    List<User> findUser(String loginName);

    /**
     * 用户修改密码
     *
     * @param user
     * @return
     */
    int updatePassWord(User user);

    /***
     * 根据组织ID 统计该组织下的用户数量
     *
     * @param orgId
     * @return
     */
    int getCountUserByOrg(Integer orgId);
    /**
     * 下拉框 搜索
     * @param query
     * @return
     */
    PageResult<Select2Type> findAllUserList(UserQuery query);

}