package com.ctw.plugins.intercepter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Spitals
 *
 * @Author: HaiAng
 * @Time： 2016/4/22.10:28
 * @Vistion：1.0
 * @Remark： 在Controller方法前进行拦截
 * 如果返回false
 * 从当前拦截器往回执行所有拦截器的afterCompletion方法,再退出拦截器链.
 * 如果返回true
 * 执行下一个拦截器,直到所有拦截器都执行完毕.
 * 再运行被拦截的Controller.
 * 然后进入拦截器链,从最后一个拦截器往回运行所有拦截器的postHandle方法.
 * 接着依旧是从最后一个拦截器往回执行所有拦截器的afterCompletion方法.
 */
public class SecurityIntercepter extends HandlerInterceptorAdapter implements InitializingBean {
    Logger logger = LoggerFactory.getLogger(SecurityIntercepter.class);
    // static GeneralCacheAdministrator cacheadmin;

    @Override
    @CacheEvict(value = "SecurityCache", allEntries = true)
    public void afterPropertiesSet() throws Exception {
       // System.out.println("=====================拦截器SecurityIntercepter初始化=================");
        logger.debug("拦截器SecurityIntercepter初始化");
        logger.info("拦截器SecurityIntercepter初始化");

    }

    /**
     * 在Controller方法前进行拦截
     * 如果返回false
     * 从当前拦截器往回执行所有拦截器的afterCompletion方法,再退出拦截器链.
     * 如果返回true
     * 执行下一个拦截器,直到所有拦截器都执行完毕.
     * 再运行被拦截的Controller.
     * 然后进入拦截器链,从最后一个拦截器往回运行所有拦截器的postHandle方法.
     * 接着依旧是从最后一个拦截器往回执行所有拦截器的afterCompletion方法.
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }
}
