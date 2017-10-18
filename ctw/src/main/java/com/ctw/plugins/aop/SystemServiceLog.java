package com.ctw.plugins.aop;

/**
 * ctw com.ctw.plugins.aop
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/17 21: 32
 * @Version 1.0
 * @explain：............
 */
import java.lang.annotation.*;

/**
 *自定义注解 拦截service
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SystemServiceLog {
    String description() default "";
}
