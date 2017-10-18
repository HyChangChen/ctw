package com.ctw.plugins.mybatis.dialect;

public class PostgreSQLDialect extends Dialect {

    public boolean supportsLimit() {
        return true;
    }

    public boolean supportsLimitOffset() {
        return true;
    }

    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        return new StringBuilder(sql.length() + 20).append(sql)
                .append(offset > 0 ? " limit " + limitPlaceholder + " offset " + offsetPlaceholder : " limit " + limitPlaceholder).toString();
    }
}
