package com.ctw.dao.dictionary;

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import com.ctw.domain.dictionary.Dictionarry;
import com.ctw.domain.dictionary.DictionarryEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.dictionary.DictionarryQuery;

import java.util.List;

public interface IDictionarryDao extends IBaseDao<Integer, DictionarryEntity> {
        int batchDelete(String[] ids);
        List<Dictionarry> findList(DictionarryQuery query);
        PageResult<Dictionarry> findPage(DictionarryQuery query);
}