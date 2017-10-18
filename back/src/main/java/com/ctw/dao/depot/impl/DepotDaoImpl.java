package com.ctw.dao.depot.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.ctw.dao.AbstractBaseDao;
import com.ctw.dao.depot.IDepotDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.depot.DepotEntity;

import com.ctw.domain.depot.Depot;
import com.ctw.domain.depot.DepotQuery;
import com.ctw.domain.depot.DepotVoConvert;

import java.util.List;

@Repository("iDepotDao")
public class DepotDaoImpl extends AbstractBaseDao<Integer, DepotEntity> implements IDepotDao {
    private static final String NAMESPACE = "com.ctw.dao.depot.IDepotDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    private void rewriteSortColumns(DepotQuery query) {

    }

    @Override
    public List<Depot> findList(DepotQuery query) {
        rewriteSortColumns(query);
        return selectList("findList", query);
    }

    @Override
    public PageResult<Depot> findPage(DepotQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findList", query);
    }

    @Override
    public int create(DepotEntity entity) {
        return super.create(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public int update(DepotEntity entity) {
        return super.update(entity);
    }

    @Override
    public DepotEntity getById(Integer id) {
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