package com.ctw.domain.common;

import java.io.Serializable;
import java.util.List;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.0:05
 * @Vistion：1.0
 * @Remark： 用于查询返回值
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -2516726606727416470L;

    private long total; // 记录总条数
    private List<T> rows;  // 当前页数据列表

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
