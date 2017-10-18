package com.ctw.dao.org.impl;


import com.ctw.dao.AbstractBaseDao;
import com.ctw.domain.common.PageResult;
import com.ctw.dao.org.IOrgDao;

import org.springframework.stereotype.Repository;

import com.ctw.domain.org.Org;
import com.ctw.domain.org.OrgEntity;
import com.ctw.domain.org.OrgQuery;


import java.util.List;
import java.util.Arrays;

@Repository("iOrgDao")
public class OrgDaoImpl extends AbstractBaseDao<Integer, OrgEntity> implements IOrgDao {
    private static final String NAMESPACE = "com.ctw.dao.org.IOrgDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    private void rewriteSortColumns(OrgQuery query) {

    }

    @Override
    public List<Org> findList(OrgQuery query) {
        rewriteSortColumns(query);
        return selectList("findList", query);
    }

    @Override
    public PageResult<Org> findPage(OrgQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findList", query);
    }

    @Override
    public int create(OrgEntity entity) {
        return super.create(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public int update(OrgEntity entity) {
        return super.update(entity);
    }

    @Override
    public OrgEntity getById(Integer id) {
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

    @Override
    public PageResult<Org> findByPartentId(OrgQuery query) {
        return pageQuery("findByPartentId", query);
    }


}