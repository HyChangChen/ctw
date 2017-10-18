package com.ctw.activiti.custommanager;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserEntityManager ;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的Activiti用户会话工厂
 *自定义的Activiti用户会话工厂， ，返回类型不要变还是 UserManager，主要是覆盖系统默认添加的用户工厂
 * Created by Administrator on 2016/11/15.
 */
public class CustomUserManagerFactory implements SessionFactory {
    private CustomUserManager userEntityManager;



    public Class<?> getSessionType() {
        // 返回原始的UserManager类型
        return UserIdentityManager.class;
    }
    public Session openSession() {
        // 返回自定义的UserManager实例
        return userEntityManager;
    }
    @Autowired
    public void setUserEntityManager(CustomUserManager userEntityManager) {
        this.userEntityManager = userEntityManager;
    }
}
