package com.ctw.plugins.filter;


import com.ctw.domain.user.User;
import com.ctw.service.resource.IResourceService;
import com.ctw.service.user.IUserService;
import com.ctw.type.Constants;
import com.ctw.utils.Functions;
import com.ctw.utils.HttpRequestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Spitals
 *
 * @Author: HaiAng
 * @Time： 2016/4/29.10:28
 * @Vistion：1.0
 * @Remark： 页面操作都是执行此方法
 */
public class SysUserFilter extends UserFilter {
    private Logger logger = LoggerFactory.getLogger(SysUserFilter.class);
    @Autowired
    private IUserService iUserService;
    @Autowired
    protected IResourceService iResourceService;
    public final static String X_R = "X-Requested-With";
    public final static String X_R_VALUE = "XMLHttpRequest";

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        /**
         * 此处采取重新获取用户的信息，包括角色，权限，菜单等  主要防新增权限以后需要重新登录系统
         */
        User user = iUserService.findUserRolds(username);
        String ipAddress = HttpRequestUtils.getClientIp(req);
        String macAddress = HttpRequestUtils.getMacAddress(ipAddress);
        if(user!=null){
            user.setIpAddress(ipAddress);
            user.setClientMac(macAddress);
        }

        String menus = "";
        /**
         * 获得用户的菜单
         */
        menus = iResourceService.findResourceHtml(user, request);
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(Constants.SIDEBAR_MENU, menus);
        user.setHtmlMenu(new StringBuffer().append(menus));
        //保存当前用户信息于session中
        session.setAttribute(Constants.CURRENT_USER, user);

        logger.debug(">> [user]requesting url: " + req.getRequestURL());
        logger.debug(">> [user]subject isAuthenticated: " + subject.isAuthenticated());
        logger.debug(">> [user]principal: " + subject.getPrincipal());
        return true;
    }


    /**
     * 加入ajax查询参数，以便跳转至超时登录页面。
     *
     * @param request
     * @param response
     * @throws IOException
     * @see org.apache.shiro.web.filter.AccessControlFilter#redirectToLogin(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    protected void redirectToLogin(ServletRequest request,
                                   ServletResponse response) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String xrv = httpServletRequest.getHeader(X_R);

        if (xrv != null && xrv.equalsIgnoreCase(X_R_VALUE)) {
            Map<String, String> queryParams = new HashMap<String, String>();
            queryParams.put("ajax", "true");
            WebUtils.issueRedirect(request, response, getLoginUrl(), queryParams);
        } else {
            super.redirectToLogin(request, response);
        }
    }

}
