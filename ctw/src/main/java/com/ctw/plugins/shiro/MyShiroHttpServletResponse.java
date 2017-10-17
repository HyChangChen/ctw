package com.ctw.plugins.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

/**
 * ctw com.ctw.plugins.shiro
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/16 12: 26
 * @Version 1.0
 * @explain：............
 */
public class MyShiroHttpServletResponse extends ShiroHttpServletResponse {

    public MyShiroHttpServletResponse(HttpServletResponse wrapped, ServletContext context, ShiroHttpServletRequest request) {
        super(wrapped, context, request);
    }

    @Override
    protected String toEncoded(String url, String sessionId) {
        // don't encode the url with sessionId, just use the origin url
        return url;
    }
}
