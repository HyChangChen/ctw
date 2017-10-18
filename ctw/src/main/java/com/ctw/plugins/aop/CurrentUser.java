package com.ctw.plugins.aop;

import com.ctw.type.Constants;

import java.lang.annotation.*;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.14:20
 * @Vistion：1.0
 * @Remark： 请输入本类的作用
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default Constants.CURRENT_USER;

}
