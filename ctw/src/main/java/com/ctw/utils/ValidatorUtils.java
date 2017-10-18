package com.ctw.utils;

import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.1:02
 * @Vistion：1.0
 * @Remark： 检验规则
 */
public class ValidatorUtils {

    private static final String ERROR_MESSAGE = "字段：{0}校验失败，错误原因：{1};";

    // 它是线程安全的
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验对象中的各字段是否符合注解定义的限制
     *
     * @param obj
     * @return 校验结果
     */
    public static <T> ValidationResult validateEntity(T obj) {
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        return buildResult(set);
    }

    /**
     * 校验对象中的指定字段是否符合注解定义的限制
     *
     * @param obj
     * @param propertyNames
     * @return
     */
    public static <T> ValidationResult validateProperties(T obj, String... propertyNames) {
        Set<ConstraintViolation<T>> allSet = null;
        if (propertyNames != null) {
            allSet = new HashSet<ConstraintViolation<T>>();
            for (String propertyName : propertyNames) {
                Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
                if (CollectionUtils.isNotEmpty(set)) {
                    allSet.addAll(set);
                }
            }
        }
        return buildResult(allSet);
    }

    /**
     * 根据校验结果集来构造错误信息
     */
    private static <T> ValidationResult buildResult(Set<ConstraintViolation<T>> constraintViolations) {
        ValidationResult result = new ValidationResult();
        StringBuilder buffer = new StringBuilder();
        if (CollectionUtils.isNotEmpty(constraintViolations)) {
            result.setHasErrors(true);
            for (ConstraintViolation<T> cv : constraintViolations) {
                // 字段名称
                String field = cv.getPropertyPath().toString();
                // 错误原因
                String message = cv.getMessage();
                String errors = MessageFormat.format(ERROR_MESSAGE, field, message);

                buffer.append(errors).append("\n");
            }
            result.setErrormsg(buffer.toString());
        }
        return result;
    }

}

