package com.ctw.activiti.custommanager;

import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 自定义的Activiti用户组管理器
 * 自定义的Activiti用户组管理器，主要重写对用户群组的增删改查等操作
 * 如果想使用自己定义的用户组表，请重新这个findGroupById(final String groupCode)
 * 和findGroupsByUser(final String userCode)，这两个方法是activiti查询的用户组的方法。
 * Created by Administrator on 2016/11/15.
 */
public class CustomGroupManager extends GroupEntityManager {
    private static final Log logger = LogFactory.getLog(CustomGroupManager.class);
    @Override
    public Group createNewGroup(String groupId) {
        // TODO Auto-generated method stub
        return super.createNewGroup(groupId);
    }

    @Override
    public void insertGroup(Group group) {
        // TODO Auto-generated method stub
        super.insertGroup(group);
    }

    @Override
    public void updateGroup(Group updatedGroup) {
        // TODO Auto-generated method stub
        super.updateGroup(updatedGroup);
    }

    @Override
    public void deleteGroup(String groupId) {
        // TODO Auto-generated method stub
        super.deleteGroup(groupId);
    }

    @Override
    public GroupQuery createNewGroupQuery() {
        // TODO Auto-generated method stub
        return super.createNewGroupQuery();
    }

    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
        // TODO Auto-generated method stub
        return super.findGroupByQueryCriteria(query, page);
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
        // TODO Auto-generated method stub
        return super.findGroupCountByQueryCriteria(query);
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        // TODO Auto-generated method stub
        return super.findGroupsByUser(userId);
    }

    @Override
    public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        // TODO Auto-generated method stub
        return super.findGroupsByNativeQuery(parameterMap, firstResult, maxResults);
    }

    @Override
    public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
        // TODO Auto-generated method stub
        return super.findGroupCountByNativeQuery(parameterMap);
    }
}
