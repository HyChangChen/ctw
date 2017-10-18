package com.ctw.plugins.spring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/27.13:16
 * @Vistion：1.0
 * @Remark： 前端ajax请求的异常处理类
 */

public class AjaxExceptionHandler extends AbstractHandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(AjaxExceptionHandler.class);

    private String ajaxErrorView;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.info("handling exception for[" + request.getRequestURI() + "]: " + ex.toString());
        if (isAjax(request)) {
            logger.warn(">> returning null view for ajax exception of[" + request.getRequestURI() + "]: " + ex.toString());
            return null;
        } else {
            ModelAndView model = new ModelAndView(ajaxErrorView);
            model.addObject("exception", ex);
            return model;
        }
    }

    private boolean isAjax(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    public String getAjaxErrorView() {
        return ajaxErrorView;
    }

    public void setAjaxErrorView(String ajaxErrorView) {
        this.ajaxErrorView = ajaxErrorView;
    }

}
