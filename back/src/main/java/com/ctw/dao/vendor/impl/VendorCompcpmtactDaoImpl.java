package com.ctw.dao.vendor.impl;
        import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
        import com.ctw.dao.AbstractBaseDao;
        import com.ctw.dao.vendor.IVendorCompcpmtactDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;
        import com.ctw.domain.common.PageResult;
import com.ctw.domain.vendor.VendorCompcpmtactEntity;

import com.ctw.domain.vendor.VendorCompcpmtact;
import com.ctw.domain.vendor.VendorCompcpmtactQuery;
import com.ctw.domain.vendor.VendorCompcpmtactVoConvert;

import java.util.List;

@Repository("iVendorCompcpmtactDao")
public class VendorCompcpmtactDaoImpl extends AbstractBaseDao<Integer, VendorCompcpmtactEntity>implements IVendorCompcpmtactDao{
private static final String NAMESPACE="com.ctw.dao.vendor.IVendorCompcpmtactDao";

@Override
protected String getNamespace(){
        return NAMESPACE;
        }

private void rewriteSortColumns(VendorCompcpmtactQuery query){

        }

@Override
public List<VendorCompcpmtact>findList(VendorCompcpmtactQuery query){
        rewriteSortColumns(query);
        return selectList("findList",query);
        }

@Override
public PageResult<VendorCompcpmtact>findPage(VendorCompcpmtactQuery query){
        rewriteSortColumns(query);
        return pageQuery("findList",query);
        }
@Override
public int create(VendorCompcpmtactEntity entity) {
        return super.create(entity);
        }
@Override
public int deleteById(Integer id) {
        return super.deleteById(id);
        }
@Override
public int update(VendorCompcpmtactEntity entity) {
        return super.update(entity);
        }
@Override
public VendorCompcpmtactEntity getById(Integer id) {
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