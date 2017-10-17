package com.ctw.dao.dictionary.impl;
        import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
        import com.ctw.dao.AbstractBaseDao;
        import com.ctw.dao.dictionary.IDictionarryDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;
        import com.ctw.domain.common.PageResult;
import com.ctw.domain.dictionary.DictionarryEntity;

import com.ctw.domain.dictionary.Dictionarry;
import com.ctw.domain.dictionary.DictionarryQuery;
import com.ctw.domain.dictionary.DictionarryVoConvert;

import java.util.List;

@Repository("iDictionarryDao")
public class DictionarryDaoImpl extends AbstractBaseDao<Integer, DictionarryEntity>implements IDictionarryDao{
private static final String NAMESPACE="com.ctw.dao.dictionary.IDictionarryDao";

@Override
protected String getNamespace(){
        return NAMESPACE;
        }

private void rewriteSortColumns(DictionarryQuery query){

        }

@Override
public List<Dictionarry>findList(DictionarryQuery query){
        rewriteSortColumns(query);
        return selectList("findList",query);
        }

@Override
public PageResult<Dictionarry>findPage(DictionarryQuery query){
        rewriteSortColumns(query);
        return pageQuery("findList",query);
        }
@Override
public int create(DictionarryEntity entity) {
        return super.create(entity);
        }
@Override
public int deleteById(Integer id) {
        return super.deleteById(id);
        }
@Override
public int update(DictionarryEntity entity) {
        return super.update(entity);
        }
@Override
public DictionarryEntity getById(Integer id) {
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