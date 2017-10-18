package com.ctw.domain.common;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.0:06
 * @Vistion：1.0
 * @Remark： 分页查询的基本类
 */
public class PageQuery extends BaseQuery {

    public static final int DEFAULT_PAGE_SIZE = 10;

    private static final long serialVersionUID = -3795622215103752896L;

    private int page; // 当前页,名字必须为page
    private int rows = DEFAULT_PAGE_SIZE; // 每页大小,名字必须为rows

    public int getPage() {
        return page > 0 ? page : 1;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows > 0 ? rows : DEFAULT_PAGE_SIZE;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStartIndex() {
        return (page - 1) * rows;
    }

    public int getEndIndex() {
        return page * rows;
    }
}
