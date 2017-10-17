package com.ctw.dao;

import com.ctw.domain.common.PageQuery;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.org.OrgQuery;
import com.ctw.exception.BusinessException;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.0:02
 * @Vistion：1.0
 * @Remark： mybatis支持的数据库访问类, 提供了基本的数据库操作方法
 */

public abstract class AbstractBaseDao<K extends Serializable, T> extends SqlSessionDaoSupport {

    private static final Logger logger = LoggerFactory.getLogger(AbstractBaseDao.class);

    /**
     * 获取命名空间字符串
     *
     * @return
     */
    protected abstract String getNamespace();

    /**
     * 构造带有命名空间前缀的SQL语句命名
     *
     * @param statementName
     * @return
     */
    protected String getNamespaceStatement(String statementName) {
        return getNamespace().concat(".").concat(statementName);
    }

    /**
     * 执行INSERT语句
     *
     * @param statementName
     * @param param
     * @return 影响的行数
     */
    protected int executeInsert(String statementName, Object param) {
        try {
            return getSqlSession().insert(getNamespaceStatement(statementName), param);
        } catch (Throwable t) {
            // 数据库操作错误时, mybatis会将错误的sql语句作为异常信息抛出
            // 为避免前端看到这些sql语句, 此处将进行一个异常转换, 将具体错误信息记录到服务器log中
            // 而只将简短的错误描述返回给前端
            logger.error("执行insert失败", t);
            throw new BusinessException("数据库操作失败");
        }
    }

    /**
     * 执行UPDATE语句
     *
     * @param statementName
     * @param param
     * @return 影响的行数
     */
    protected int executeUpdate(String statementName, Object param) {
        try {
            return getSqlSession().update(getNamespaceStatement(statementName), param);
        } catch (Throwable t) {
            // 数据库操作错误时, mybatis会将错误的sql语句作为异常信息抛出
            // 为避免前端看到这些sql语句, 此处将进行一个异常转换, 将具体错误信息记录到服务器log中
            // 而只将简短的错误描述返回给前端
            logger.error("执行update失败", t);
            throw new BusinessException("数据库操作失败");
        }
    }

    /**
     * 执行DELETE语句
     *
     * @param statementName
     * @param param
     * @return 影响的行数
     */
    protected int executeDelete(String statementName, Object param) {
        try {
            return getSqlSession().delete(getNamespaceStatement(statementName), param);
        } catch (Throwable t) {
            System.out.print("T:" + t);
            // 数据库操作错误时, mybatis会将错误的sql语句作为异常信息抛出
            // 为避免前端看到这些sql语句, 此处将进行一个异常转换, 将具体错误信息记录到服务器log中
            // 而只将简短的错误描述返回给前端
            logger.error("执行delete失败", t);
            throw new BusinessException("数据库操作失败");
        }
    }



    /**
     * 根据条件获取唯一的结果对象
     *
     * @param statementName
     * @param param
     * @return
     */
    protected <E> E selectOne(String statementName, Object param) {
        try {
            return getSqlSession().selectOne(getNamespaceStatement(statementName), param);
        } catch (Throwable t) {
            System.out.println("t:" + t);
            // 数据库操作错误时,
            // 为避免前端看到这些sql语句, 此处将进行一个异常转换, 将具体错误信息记录到服务器log中
            // 而只将简短的错误描述返回给前端
            logger.error("执行selectOne失败", t);
            throw new BusinessException("获取数据时发生错误");
        }
    }

    /**
     * 根据条件获取对象列表
     *
     * @param statementName
     * @param param
     * @return
     */
    protected <E> List<E> selectList(String statementName, Object param) {
        try {
            return getSqlSession().selectList(getNamespaceStatement(statementName), param);
        } catch (Throwable t) {
            // 数据库操作错误时, mybatis会将错误的sql语句作为异常信息抛出
            // 为避免前端看到这些sql语句, 此处将进行一个异常转换, 将具体错误信息记录到服务器log中
            // 而只将简短的错误描述返回给前端
            logger.error("执行selectOne失败", t);
            throw new BusinessException("获取数据时发生错误");
        }
    }

    /**
     * 根据query对象的条件, 进行分页查询
     *
     * @param statementName
     * @param query
     * @return
     */
    protected <E> PageResult<E> pageQuery(String statementName, PageQuery query) {
        return pageQuery(statementName, query, query.getStartIndex(), query.getRows());
    }

    /**
     * 根据params对象的条件, 获取分页数据
     *
     * @param statementName
     * @param params
     * @param offset
     * @param limit
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected <E> PageResult<E> pageQuery(String statementName, Object params, int offset, int limit) {
        // 1. 获取符合条件的记录总数
        Long totalCount = selectOne(statementName + "Count", params);
        PageResult page = new PageResult();
        page.setTotal(totalCount.intValue());
        // 2. 只有存在符合条件的记录时, 才需要查询结果
        if (totalCount > 0) {
            List result;
            try {
                result = getSqlSession().selectList(getNamespaceStatement(statementName), params,
                        new RowBounds(offset, limit));
            } catch (Throwable t) {
                // 数据库操作错误时, mybatis会将错误的sql语句作为异常信息抛出
                // 为避免前端看到这些sql语句, 此处将进行一个异常转换, 将具体错误信息记录到服务器log中
                // 而只将简短的错误描述返回给前端
                logger.error("执行pageQuery失败,获取记录数据时发生错误", t);
                throw new BusinessException("获取数据时发生错误");
            }
            page.setRows(result);
        } else {
            page.setRows(new ArrayList());
        }
        return page;
    }

    /**
     * 覆盖该方法进行保存前的操作
     *
     * @param entity
     */
    protected void beforeSave(T entity) {
        // override this method to do jobs before save
    }

    /**
     * 创建entity对象的数据库记录
     *
     * @param entity
     */
    public int create(T entity) {
        beforeSave(entity);
        return executeInsert("create", entity);
    }

    /**
     * 根据ID删除对象
     *
     * @param id
     */
    public int deleteById(K id) {
        return executeDelete("deleteById", id);
    }

    /**
     * 根据entity的ID,更新entity对象
     *
     * @param entity
     */
    public int update(T entity) {
        return executeUpdate("update", entity);
    }

    /**
     * 根据ID获取对象
     *
     * @param id
     * @return
     */
    public T getById(K id) {
        return selectOne("getById", id);
    }

}

