package com.ctw.dao.vendor.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.ctw.dao.AbstractBaseDao;
import com.ctw.dao.vendor.IVendorDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.vendor.VendorEntity;
import com.ctw.domain.vendor.Vendor;
import com.ctw.domain.vendor.VendorQuery;
import com.ctw.domain.vendor.VendorVoConvert;

import java.util.List;

@Repository("iVendorDao")
public class VendorDaoImpl extends AbstractBaseDao<Integer, VendorEntity> implements IVendorDao {
    private static final String NAMESPACE = "com.ctw.dao.vendor.IVendorDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    private void rewriteSortColumns(VendorQuery query) {

    }

    @Override
    public List<Vendor> findList(VendorQuery query) {
        rewriteSortColumns(query);
        return selectList("findList", query);
    }

    @Override
    public PageResult<Vendor> findPage(VendorQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findList", query);
    }

    @Override
    public int create(VendorEntity entity) {
        return super.create(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public int update(VendorEntity entity) {
        return super.update(entity);
    }

    @Override
    public VendorEntity getById(Integer id) {
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