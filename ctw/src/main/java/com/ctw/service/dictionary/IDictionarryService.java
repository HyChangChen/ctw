package com.ctw.service.dictionary;

import com.ctw.domain.dictionary.Dictionarry;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.dictionary.DictionarryQuery;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface IDictionarryService extends IBaseService<Integer ,Dictionarry> {
    int batchDelete(String[] ids);
    List<Dictionarry> findList(DictionarryQuery query);
    PageResult<Dictionarry> findPage(DictionarryQuery query);
}