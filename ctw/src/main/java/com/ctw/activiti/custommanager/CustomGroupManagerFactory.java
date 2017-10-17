package com.ctw.activiti.custommanager;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager ;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 自定义的Activiti用户组会话工厂
* Created by Administrator on 2016/11/15.
 */
public class CustomGroupManagerFactory implements SessionFactory {
    private CustomGroupManager groupEntityManager;


    public Class<?> getSessionType() {
        // 返回原始的GroupEntityManager 类型
        return GroupIdentityManager.class;
    }
    public Session openSession() {
        // 返回自定义的GroupEntityManager 实例
        return groupEntityManager;
    }



    public GroupEntityManager getGroupEntityManager() {
        return groupEntityManager;
    }
    @Autowired
    public void setGroupEntityManager(CustomGroupManager groupEntityManager) {
        this.groupEntityManager = groupEntityManager;
    }
}
