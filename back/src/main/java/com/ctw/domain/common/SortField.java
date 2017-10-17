package com.ctw.domain.common;

import java.io.Serializable;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.0:08
 * @Vistion：1.0
 * @Remark： 排序的字段
 */
public class SortField implements Serializable {
    private static final long serialVersionUID = -1972984606086499282L;

    private String fieldName; // 排序的字段名
    private boolean isDesc;

    public SortField() {
    }

    public SortField(String sortField, boolean isDesc) {
        this.fieldName = sortField;
        this.isDesc = isDesc;
    }

    public SortField(String sortField, String order) {
        this.fieldName = sortField;
        boolean isDesc = false;
        if (order.toUpperCase().equals("DESC")) {
            isDesc = true;
        }
        this.isDesc = isDesc;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isDesc() {
        return isDesc;
    }

    public void setDesc(boolean isDesc) {
        this.isDesc = isDesc;
    }
}

