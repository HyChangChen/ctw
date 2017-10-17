package com.ctw.plugins.filter;


import com.ctw.domain.user.User;
import com.ctw.exception.BusinessException;
import com.ctw.utils.Functions;
import com.ctw.utils.HttpRequestUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Spitals
 *
 * @Author: HaiAng
 * @Time： 2016/5/22.14:34
 * @Vistion：1.0
 * @Remark： 请输入本类的作用
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    public CustomFormAuthenticationFilter() {
        setLoginUrl(DEFAULT_LOGIN_URL);
    }

    private static final Logger log = LoggerFactory
            .getLogger(CustomFormAuthenticationFilter.class);



    /**
     * 覆盖isAccessAllowed，改变shiro的验证逻辑。
     * 避免不能多次登录的错误。
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @see org.apache.shiro.web.filter.authc.AuthenticatingFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            // 先判断是否是登录操作
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return false;
            }
        } catch (Exception e) {
            log.error(BusinessException.getStackTraceAsString(e));
        }

        return super.isAccessAllowed(request, response, mappedValue);
    }

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {


        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;




        //不是ajax请求
        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest
                .getHeader("X-Requested-With"))) {
            issueSuccessRedirect(request, response);
        } else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.print("{\"success\":true,\"message\":\"登入成功\"}");
            out.flush();
            out.close();
        }

        return false;
    }

    /**
     *  * 覆盖默认实现，打印日志便于调试，查看具体登录是什么错误。
     *（可以扩展把错误写入数据库之类的。）
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e, ServletRequest request,
                                     ServletResponse response) {

        // 不是ajax请求
        if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
                .getHeader("X-Requested-With"))) {
            setFailureAttribute(request, e);
            return true;
        }
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
                out.print("{\"success\":false,\"message\":\"密码错误\"}");
            } else if ("UnknownAccountException".equals(message)) {
                out.print("{\"success\":false,\"message\":\"账号不存在\"}");
            } else if ("LockedAccountException".equals(message)) {
                out.print("{\"success\":false,\"message\":\"账号被锁定\"}");
            } else {
                out.print("{\"success\":false,\"message\":\"未知错误\"}");
            }
            out.flush();
            out.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }

    /**
     * 所有请求都会经过的方法。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {


        if (isLoginRequest(request, response)) {
            //POST
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }

                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }

                // 不是ajax请求
                if ("XMLHttpRequest"
                        .equalsIgnoreCase(((HttpServletRequest) request)
                                .getHeader("X-Requested-With"))) {
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    //redirect
                    out.print("{\"httpStatus\":302,\"message\":\"login\"}");
                    out.flush();
                    out.close();
                    return false;
                }

                // allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
                        + "Authentication url [" + getLoginUrl() + "]");
            }
            // 不是ajax请求
            if (!"XMLHttpRequest"
                    .equalsIgnoreCase(((HttpServletRequest) request)
                            .getHeader("X-Requested-With"))) {
                saveRequestAndRedirectToLogin(request, response);
            } else {
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print("{\"message\":\"login\"}");
                out.flush();
                out.close();
            }
            return false;
        }
    }

}
