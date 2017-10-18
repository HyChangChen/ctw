package com.ctw.service.dictionary.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.dictionary.IDictionarryDao;
import com.ctw.domain.dictionary.DictionarryEntity;
        import com.ctw.domain.common.PageResult;
import com.ctw.domain.dictionary.Dictionarry;
import com.ctw.domain.dictionary.DictionarryQuery;
import com.ctw.domain.dictionary.DictionarryVoConvert;
 import com.ctw.service.dictionary.IDictionarryService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class DictionarryServiceImpl implements IDictionarryService {

    @Autowired
    private IDictionarryDao iDictionarryDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(Dictionarry dictionarry) {
        if (null == dictionarry) {
            return 0;
        }
      /*  if(StringUtils.isNotBlank(dictionarry.getId().toString())){
            dictionarry.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(dictionarry);
        DictionarryEntity dictionarryEntity = DictionarryVoConvert.toEntity(dictionarry);
        return iDictionarryDao.create(dictionarryEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(Dictionarry dictionarry) {
        if(StringUtils.isEmpty(dictionarry.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(dictionarry);
        DictionarryEntity dictionarryEntity = DictionarryVoConvert.toEntity(dictionarry);
        return iDictionarryDao.update(dictionarryEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iDictionarryDao.deleteById(id);
    }
    @Override
    public int batchDelete(String[] ids) {
        return iDictionarryDao.batchDelete(ids);
    }

    @Override
    public Dictionarry getById(Integer id) {
        return DictionarryVoConvert.toVo(iDictionarryDao.getById(id));
    }

    @Override
    public List<Dictionarry> findList(DictionarryQuery query) {
        return iDictionarryDao.findList(query);
    }

    @Override
    public PageResult<Dictionarry> findPage(DictionarryQuery query) {
        return iDictionarryDao.findPage(query);
    }
}