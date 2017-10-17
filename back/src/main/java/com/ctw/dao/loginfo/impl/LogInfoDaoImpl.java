package com.ctw.dao.loginfo.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.ctw.dao.AbstractBaseDao;
import com.ctw.dao.loginfo.ILogInfoDao;
import com.ctw.domain.common.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.domain.loginfo.LogInfoEntity;

import com.ctw.domain.loginfo.LogInfo;
import com.ctw.domain.loginfo.LogInfoQuery;


import java.util.List;

@Repository("iLogInfoDao")
public class LogInfoDaoImpl extends AbstractBaseDao<Integer, LogInfoEntity> implements ILogInfoDao {
    private static final String NAMESPACE = "com.ctw.dao.loginfo.ILogInfoDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    private void rewriteSortColumns(LogInfoQuery query) {

    }

    @Override
    public List<LogInfo> findList(LogInfoQuery query) {
        rewriteSortColumns(query);
        return selectList("findList", query);
    }

    @Override
    public PageResult<LogInfo> findPage(LogInfoQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findList", query);
    }

    @Override
    public int create(LogInfoEntity entity) {
        return super.create(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public int update(LogInfoEntity entity) {
        return super.update(entity);
    }

    @Override
    public LogInfoEntity getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public int batchDelete(String[] ids) {
        int i = 0;
        List<String> idsTemp = Arrays.asList(ids);
        if (idsTemp.size() > 0) {
            for (String id : idsTemp) {
                i += super.deleteById(Integer.parseInt(id));
            }
        }
        return i;
    }

}