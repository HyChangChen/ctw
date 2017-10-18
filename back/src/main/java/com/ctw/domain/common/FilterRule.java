package com.ctw.domain.common;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.1:04
 * @Vistion：1.0
 * @Remark： 请输入本类的作用
 */
public class FilterRule {
    private String field;
    private String op;
    private String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
