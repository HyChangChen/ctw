package com.ctw.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.14:47
 * @Vistion：1.0
 * @Remark： Controller 异常处理
 */
@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView handlerArithmeticException(Exception ex) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex);
        return mv;
    }
}
