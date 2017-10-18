package com.ctw.dao.depot;

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import com.ctw.domain.depot.Depot;
import com.ctw.domain.depot.DepotEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.depot.DepotQuery;

import java.util.List;

public interface IDepotDao extends IBaseDao<Integer, DepotEntity> {
        int batchDelete(String[] ids);
        List<Depot> findList(DepotQuery query);
        PageResult<Depot> findPage(DepotQuery query);
}