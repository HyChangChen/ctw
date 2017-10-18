package com.ctw.plugins.mybatis.dialect;

public class MySQLDialect extends Dialect {

    public boolean supportsLimitOffset() {
        return true;
    }

    public boolean supportsLimit() {
        return true;
    }

    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        StringBuilder buffer = new StringBuilder(sql);
        if (offset > 0) {
            buffer.append(" limit ").append(offsetPlaceholder).append(",").append(limitPlaceholder);
        } else {
            buffer.append(" limit ").append(limitPlaceholder);
        }
        return buffer.toString();
    }

}
