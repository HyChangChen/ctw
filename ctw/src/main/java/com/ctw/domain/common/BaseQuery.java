package com.ctw.domain.common;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.0:07
 * @Vistion：1.0
 * @Remark： 用于传递查询参数的父类
 */
public class BaseQuery   implements Serializable {

    public static final String ORDER_TYPE_ASC = "ASC";
    public static final String ORDER_TYPE_DESC = "DESC";
    public static final String WHITESPACE = " ";
    public static final String COMMA = ",";

    private static final long serialVersionUID = 5985785083546695033L;

    private List<SortField> sortFields = new ArrayList<SortField>();
    private String sortColumnsSql;
    private String sortColumns;//排序字段,多个用逗号隔开,如name,age
    private String order;//和排序字段对应的排序方式,多个逗号隔开,如asc,desc

    private String buildOrderFieldSql() {
        StringBuilder sb = new StringBuilder("");
        if (StringUtils.isNotEmpty(sortColumns) && StringUtils.isNotEmpty(sortColumns)) {
            String[] sortNames = sortColumns.split(","), sortOrders = order.split(",");
            for (int i = 0, len = sortNames.length; i < len; i++) {
                String sortName = sortNames[i], sortOrder = sortOrders[i];
                addSortField(sortName, sortOrder);
            }
        }

        if (this.getSortFields().size() > 0) {
            boolean isFirst = true;
            for (SortField field : this.getSortFields()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(COMMA);
                }
                sb.append(field.getFieldName()).append(WHITESPACE).append(field.isDesc() ? ORDER_TYPE_DESC : ORDER_TYPE_ASC);
            }
        }
        return sb.toString();
    }

    /**
     * 需要改写默认的排序sql语句时才使用
     * @param value
     */
    public void prepareSortColumns(String value) {
        this.sortColumnsSql = value;
    }

    /**
     * 返回 排序字段的 sql语句，call by Mybatis only in *Mapper.xml
     * @return
     */
    public String getSortColumnsSql() {
        if (this.sortColumnsSql == null) {
            this.sortColumnsSql = buildOrderFieldSql();
        }
        return this.sortColumnsSql;
    }

    /**
     * 返回排序列List，支持多列排序
     * @return List<SortField> readonly 防止sortFields在外部被修改
     */
    @SuppressWarnings("unchecked")
    public List<SortField> getSortFields() {
        return (ArrayList<SortField>) ((ArrayList<SortField>) this.sortFields).clone();
    }

    /**
     * 新增一个排序列
     * @param sortField
     * @param isDesc    true/false
     */
    public void addSortField(String sortField, boolean isDesc) {
        SortField field = new SortField(sortField, isDesc);
        this.sortFields.add(field);
        // 增加新的排序列，重写排序sql
        this.sortColumnsSql = buildOrderFieldSql();
    }

    /**
     * 新增一个排序列， 重载方便controller使用
     * @param sortField
     * @param order     "desc" / "asc"
     */
    public void addSortField(String sortField, String order) {
        boolean isDesc = false;
        if (StringUtils.isBlank(sortField)) {
            return;
        }
        if (!StringUtils.isBlank(order) && order.toUpperCase().equals("DESC")) {
            isDesc = true;
        }
        addSortField(sortField, isDesc);
    }

    /**
     * 新增一个排序列,并放在最前作为主序列
     * @param sortField
     * @param isDesc    true/false
     */
    public void addSortFirstField(String sortField, boolean isDesc) {
        SortField field = new SortField(sortField, isDesc);
        this.sortFields.add(0, field);
        // 增加新的排序列，重写排序sql
        this.sortColumnsSql = buildOrderFieldSql();
    }

    public String getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(String sortColumns) {
        this.sortColumns = sortColumns;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

}

