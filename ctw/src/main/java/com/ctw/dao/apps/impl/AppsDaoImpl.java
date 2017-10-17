package com.ctw.dao.apps.impl;
        import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
        import com.ctw.dao.AbstractBaseDao;
        import com.ctw.dao.apps.IAppsDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;
        import com.ctw.domain.common.PageResult;
import com.ctw.domain.apps.AppsEntity;

import com.ctw.domain.apps.Apps;
import com.ctw.domain.apps.AppsQuery;
import com.ctw.domain.apps.AppsVoConvert;

import java.util.List;

@Repository("iAppsDao")
public class AppsDaoImpl extends AbstractBaseDao<Integer, AppsEntity>implements IAppsDao{
private static final String NAMESPACE="com.ctw.dao.apps.IAppsDao";

@Override
protected String getNamespace(){
        return NAMESPACE;
        }

private void rewriteSortColumns(AppsQuery query){

        }

@Override
public List<Apps>findList(AppsQuery query){
        rewriteSortColumns(query);
        return selectList("findList",query);
        }

@Override
public PageResult<Apps>findPage(AppsQuery query){
        rewriteSortColumns(query);
        return pageQuery("findList",query);
        }
@Override
public int create(AppsEntity entity) {
        return super.create(entity);
        }
@Override
public int deleteById(Integer id) {
        return super.deleteById(id);
        }
@Override
public int update(AppsEntity entity) {
        return super.update(entity);
        }
@Override
public AppsEntity getById(Integer id) {
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