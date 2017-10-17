package com.ctw.dao;

import java.io.Serializable;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/27.23:59
 * @Vistion：1.0
 * @Remark： 请输入本类的作用
 */
public interface IBaseDao<K extends Serializable, T> {
    /**
     * 创建entity对象的数据库记录
     *
     * @param entity
     * @return 影响的行数
     */
    int create(T entity);

    /**
     * 根据ID删除对象
     *
     * @param id
     * @return 影响的行数
     */
    int deleteById(K id);

    /**
     * 根据entity的ID,更新entity对象
     *
     * @param entity
     * @return 影响的行数
     */
    int update(T entity);

    /**
     * 根据ID获取对象
     *
     * @param id
     * @return 未找到则返回<code>null</code>
     */
    T getById(K id);
}

