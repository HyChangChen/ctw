package com.ctw.plugins.mybatis;


import com.ctw.plugins.mybatis.dialect.Dialect;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/27.13:42
 * @Vistion：1.0
 * @Remark： 为mybatis3提供基于方言(Dialect)的分页查询的插件
 * <p/>
 * 将拦截Executor.query()方法实现分页方言的插入.
 * <p/>
 * 配置文件内容:
 * <p/>
 * <pre>
 * &lt;plugins>
 *     &lt;plugin interceptor="com.gzkit.plugin.mybatis.OffsetLimitInterceptor">
 *         &lt;property name="dialectClass" value="com.gzkit.plugin.mybatis.dialect.MySQLDialect"/>
 *     &lt;/plugin>
 * &lt;/plugins>
 * </pre>
 */

@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class OffsetLimitInterceptor implements Interceptor {
    private static int MAPPED_STATEMENT_INDEX = 0;
    private static int PARAMETER_INDEX = 1;
    private static int ROWBOUNDS_INDEX = 2;
    //private static int RESULT_HANDLER_INDEX = 3;

    private Dialect dialect;

    public Object intercept(Invocation invocation) throws Throwable {
        processIntercept(invocation.getArgs());
        return invocation.proceed();
    }

    private void processIntercept(final Object[] queryArgs) {
        // queryArgs = query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
        MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        Object parameter = queryArgs[PARAMETER_INDEX];
        final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
        int offset = rowBounds.getOffset();
        int limit = rowBounds.getLimit();

        if (dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
            BoundSql boundSql = ms.getBoundSql(parameter);
            String sql = boundSql.getSql().trim();
            if (dialect.supportsLimitOffset()) {
                sql = dialect.getLimitString(sql, offset, limit);
                offset = RowBounds.NO_ROW_OFFSET;
            } else {
                sql = dialect.getLimitString(sql, 0, limit);
            }
            limit = RowBounds.NO_ROW_LIMIT;

            queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset, limit);

            BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);

            MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
            queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
        }
    }

    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    // see: MapperBuilderAssistant
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.keyProperty(ms.getId());

        // setStatementTimeout()
        builder.timeout(ms.getTimeout());

        // setStatementResultMap()
        builder.parameterMap(ms.getParameterMap());

        // setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());

        // setStatementCache()
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    // 拦截器对应的封装原始对象的方法
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    // 设置注册拦截器时设定的属性
    public void setProperties(Properties properties) {
        String dialectClass = properties.getProperty("dialectClass");
        try {
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("cannot create dialect instance by dialectClass: " + dialectClass, e);
        }
        // System.out.println(OffsetLimitInterceptor.class.getSimpleName() + ".dialect=" + dialectClass);
    }

    public static class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

}
